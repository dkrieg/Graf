package graf.example

import graf._
import graf.gremlin.structure.convert.decorateAll._
import graf.gremlin.structure.syntax._
import graf.gremlin.structure.util.TinkerGraphFactory
import graf.gremlin.structure.util.show._
import org.apache.tinkerpop.gremlin.structure.io.IoCore

import scala.language.postfixOps
import scalaz.Scalaz._

object GrafApp extends App {

  // create a script to modify and traverse a graph
  val script = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      marko =  g + (Person,   Name("marko"),  Age(29))
      vadas =  g + (Person,   Name("vadas"),  Age(27))
      lop =    g + (Software, Name("lop"),    Lang("java"))
      josh =   g + (Person,   Name("josh"),   Age(32))
      ripple = g + (Software, Name("ripple"), Lang("java"))
      peter =  g + (Person,   Name("peter"),  Age(35))

      // link vertices to edges
      _ = marko --- (Knows,   Weight(0.5d)) --> vadas
      _ = marko --- (Knows,   Weight(1.0d)) --> josh
      _ = marko --- (Created, Weight(0.4d)) --> lop
      _ = josh  --- (Created, Weight(1.0d)) --> ripple
      _ = josh  --- (Created, Weight(0.4d)) --> lop
      _ = peter --- (Created, Weight(0.2d)) --> lop

      // map over all edges to create a sorted list
      eq = g.traversal.E().asScala.toList sortWith { (a, b) ⇒
        Ordering[Long].lt(a.id.asInstanceOf[Long], b.id.asInstanceOf[Long])
      }

      // yield the sorted list of Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraphFactory.open()

  // apply a Graph instance to the script to create an runnable Task
  val task = script(graph)
  println(graph)
  script(graph)
  script.bind(graph) // alternate syntax
  script.bind(graph) // bind the graph to the script gives you a new One Time Task - but does not alter the graph
  script.bind(graph)
  println(graph)
  // NOTE: we are ready to change the world but it remains unchanged!

  task.run
  println(graph)
  task.run
  task.run // The task is referentially transparent - it executes once and memoizes the results
  task.run
  println(graph)

  // print resulting list of strings to console
  task.run.foreach(println)

  // output the graph
  graph.io(IoCore.graphson()).writer.create().writeGraph(Console.out, graph)

  //  close the Graph
  graph.close()

  // NOTE: We Have Changed The World!
}
