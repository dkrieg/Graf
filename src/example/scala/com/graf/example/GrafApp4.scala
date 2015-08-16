package com.graf.example

import com.graf._
import gremlin.scala._
import org.apache.tinkerpop.gremlin.structure.io.IoCore.graphson
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

import scala.language.postfixOps
import scalaz.Scalaz._

object GrafApp4 extends App {

  // create a script to modify and traverse a graph
  val script = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      _ = g + Map(Name("blueprints"), YearCreated(2010), Software)
      _ = g.V.has(Name("blueprints")).head <-- "dependsOn" --- (g + Map(Name("gremlin"), Software, YearCreated(2009)))
      _ = g.V.has(Name("gremlin")).head <-- "dependsOn" --- (g + Map(Name("gremlinScala"), Software))
      _ = g.V.has(Name("gremlinScala")).head <-- "createdBy" --- (g + Map(Person, Name("mpollmeier")))

      // map over all edges to create a sorted list
      eq = g.E.toList() sortWith { (a, b) ⇒
        a.id.asInstanceOf[Long].compareTo(b.id.asInstanceOf[Long]) < 0
      }

      // yield the sorted list of Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraph.open

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
  graph.io(graphson()).writer.create.writeGraph(Console.out, graph)

  //  close the Graph
  graph.close()

  // NOTE: We Have Changed The World!
}
