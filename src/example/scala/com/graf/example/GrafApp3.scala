package com.graf.example

import com.graf._
import gremlin.scala._
import org.apache.tinkerpop.gremlin.structure.io.IoCore.graphson
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import shapeless.{::, HNil, Poly1}

import scala.language.postfixOps
import scalaz.Scalaz._
import scalaz.concurrent.Task
import scalaz.stream.Process
import scalaz.stream.Process.emit

object GrafApp3 extends App {

  // declare some values
  object task extends Poly1 {
    implicit def caseTaskU = at[Process[Task, Task[Unit]]](_.eval.runLog.run)

    implicit def caseTaskL = at[Process[Task, Task[List[String]]]](_.eval.runLog.run.flatten)
  }

  val createVertices = Graf {
    for {
      // access the Graph
      g ← G

      // create some vertices
      _ = g ++ (person, name("marko"), age(29))
      _ = g ++ (person, name("vadas"), age(27))
      _ = g ++ (software, name("lop"), lang("java"))
      _ = g ++ (person, name("josh"), age(32))
      _ = g ++ (software, name("ripple"), lang("java"))
      _ = g ++ (person, name("peter"), age(35))
    } yield ()
  }

  // map over vertices to find and link vertices to edges
  val createEdges = Graf {
    for {
      // access the Graph
      g ← G

      // map vertices by name
      v = g.V.toList().foldLeft(Map.empty[String, Vertex]) { (b, v) ⇒
        b.updated(v.value("name"), v)
      }

      // create edges
      _ = (v("marko") -- knows -> v("vadas")).weight(0.5d)
      _ = (v("marko") -- knows -> v("josh")).weight(1.0d)
      _ = (v("marko") -- created -> v("lop")).weight(0.4d)
      _ = (v("josh") -- created -> v("ripple")).weight(1.0d)
      _ = (v("josh") -- created -> v("lop")).weight(0.4d)
      _ = (v("peter") -- created -> v("lop")).weight(0.2d)
    } yield ()
  }

  // map over all edges to create a sorted list
  val getSortedEdges = Graf {
    for {
      // access the Graph
      g ← G

      eq = g.E.toList() sortWith { (a, b) ⇒
        a.id.asInstanceOf[Long].compareTo(b.id.asInstanceOf[Long]) < 0
      }
      //yield a sorted list of the Show[Edge] strings for all edges
    } yield eq map (_.shows)
  }

  // compose several Graf instances together to create a list Processes
  type GHList = Process[Task, Task[Unit]] :: Process[Task, Task[Unit]] :: Process[Task, Task[List[String]]] :: HNil
  val script: Graf[GHList] = for {
    a ← createVertices
    b ← createEdges
    c ← getSortedEdges
  } yield emit(a).toSource :: emit(b).toSource :: emit(c).toSource :: HNil
  // NOTE: nothing has happened - the world is unchanged!

  // open a Graph
  val graph = TinkerGraph.open

  // apply a Graph instance to the script to create a list of runnable Processes
  val taskList: GHList = script(graph)
  // NOTE: we are ready to change the world but it remains unchanged!

  // The task is referentially transparent - it executes once and memoizes the results
  taskList.map(task)
  taskList.map(task)
  taskList.map(task)
  taskList.map(task)
  taskList.map(task).last.foreach(println)

  // output the graph
  graph.io(graphson()).writer.create.writeGraph(Console.out, graph)

  //  close the Graph
  graph.close()

  // NOTE: We Have Changed The World!
}
