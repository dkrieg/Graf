package graf.example

import graf._
import graf.gremlin.structure._
import graf.gremlin.structure.convert.decorateAll._
import graf.gremlin.structure.syntax._
import graf.gremlin.structure.util.TinkerGraphFactory
import graf.gremlin.structure.util.show._
import org.apache.tinkerpop.gremlin.structure.io.IoCore

import scala.language.postfixOps
import scalaz.Scalaz._

object GrafApp4 extends App {
  // create a script to modify and traverse a graph
  val script = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      _ = g + (Software, Name("blueprints"), YearCreated(2010))
      _ = g.traversal.V().hasKeyValue(Name("blueprints")).asScala.toList.head <-- "dependsOn" --- (g + (Software, Name("gremlin"), YearCreated(2009)))
      _ = g.traversal.V().hasKeyValue(Name("gremlin")).asScala.toList.head <-- "dependsOn" --- (g + (Software, Name("gremlinScala")))
      _ = g.traversal.V().hasKeyValue(Name("gremlinScala")).asScala.toList.head <-- "createdBy" --- (g + (Person, Name("mpollmeier")))

      // map over all edges to create a sorted list
      eq = g.traversal.E().asScala.toList.sortWith { (a, b) ⇒
        Ordering[Long].lt(a.id.asInstanceOf[Long], b.id.asInstanceOf[Long])
      }

      // yield the sorted list of Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraphFactory.open()

  // apply a Graph instance to the script to create an runnable Task
  val task = script.bind(graph)
  println(graph)
  script.bind(graph)
  script.bind(graph) // bind the graph to the script gives you a new One Time Task - but does not alter the graph
  script.bind(graph)
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
