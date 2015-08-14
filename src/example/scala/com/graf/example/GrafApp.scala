package com.graf.example

import com.graf._
import gremlin.scala._
import org.apache.tinkerpop.gremlin.structure.io.IoCore.graphson
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

import scala.language.postfixOps
import scalaz.Scalaz._

object GrafApp extends App {

  // create a script to modify and traverse a graph
  val script = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      marko = g + (person, Map(name("marko"), age(29)))
      vadas = g + (person, Map(name("vadas"), age(27)))
      lop = g + (software, Map(name("lop"), lang("java")))
      josh = g + (person, Map(name("josh"), age(32)))
      ripple = g + (software, Map(name("ripple"), lang("java")))
      peter = g + (person, Map(name("peter"), age(35)))

      // link vertices to edges
      _ = marko --- knows --> vadas weight 0.5d
      _ = marko --- knows --> josh weight 1.0d
      _ = marko --- created --> lop weight 0.4d
      _ = josh --- created --> ripple weight 1.0d
      _ = josh --- created --> lop weight 0.4d
      _ = peter --- created --> lop weight 0.2d

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
