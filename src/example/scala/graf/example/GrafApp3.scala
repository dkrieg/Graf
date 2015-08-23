package graf.example

import graf._
import graf.gremlin.structure._
import graf.gremlin.structure.io._
import graf.gremlin.structure.util.show._
import graf.gremlin.structure.util.TinkerGraphFactory
import shapeless.{ ::, HNil }

import scala.language.postfixOps
import scalaz.Scalaz._
import scalaz.concurrent.Task
import scalaz.stream.Process

object GrafApp3 extends App {

  // declare some values
  val createVertices = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      _ = g + (Person,   Name("marko"),  Age(29))
      _ = g + (Person,   Name("vadas"),  Age(27))
      _ = g + (Software, Name("lop"),    Lang("java"))
      _ = g + (Person,   Name("josh"),   Age(32))
      _ = g + (Software, Name("ripple"), Lang("java"))
      _ = g + (Person,   Name("peter"),  Age(35))
    } yield ()
  }

  // map over vertices to find and link vertices to edges
  val createEdges = Graf {
    for {
      // access the Graph
      g ← G

      // map vertices by name
      v = g.vertices.toList.foldLeft(Map.empty[String, GrafVertex]) { (b, v) ⇒
        b.updated(v.value("name"), v)
      }

      // create edges
      _ = v("marko") --- (Knows,   Weight(0.5d)) --> v("vadas")
      _ = v("marko") --- (Knows,   Weight(1.0d)) --> v("josh")
      _ = v("marko") --- (Created, Weight(0.4d)) --> v("lop")
      _ = v("josh")  --- (Created, Weight(1.0d)) --> v("ripple")
      _ = v("josh")  --- (Created, Weight(0.4d)) --> v("lop")
      _ = v("peter") --- (Created, Weight(0.2d)) --> v("lop")
    } yield ()
  }

  // map over all edges to create a sorted list
  val getSortedEdges = Graf {
    for {
      // access the Graph
      g ← G

      eq = g.edges.toList sortWith { (a, b) ⇒
        a.id[Long].compareTo(b.id[Long]) < 0
      }
      //yield a sorted list of the Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }

  // compose several Graf instances together to create a list Processes
  val script: Graf[Process[Task, Unit :: Unit :: List[String] :: HNil]] = Graf {
    for {
      a ← createVertices
      b ← createEdges
      c ← getSortedEdges
    } yield Process(a :: b :: c :: HNil).toSource
  }
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraphFactory.open()

  // apply a Graph instance to the script to create a list of runnable Processes
  val task: OneTimeTask[Process[Task, Unit :: Unit :: List[String] :: HNil]] = script.bind(graph)
  println(graph)
  script.bind(graph)
  script.bind(graph) // bind the graph to the script gives you a new One Time Task - but does not alter the graph
  script.bind(graph)
  script.bind(graph)
  println(graph)
  // NOTE: we are ready to change the world but it remains unchanged!

  // The task is referentially transparent - it executes once and memoizes the results
  task.run
  println(graph)
  task.run
  task.run // The task is referentially transparent - it executes once and memoizes the results
  task.run
  println(graph)

  task.run.runLog.run.head.last.foreach(println)

  // output the graph
  graph.io(GrafIO.GraphSON).writer.create().writeGraph(Console.out, graph)

  //  close the Graph
  graph.close()

  // NOTE: We Have Changed The World!
}
