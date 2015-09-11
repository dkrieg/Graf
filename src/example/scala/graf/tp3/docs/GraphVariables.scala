package graf.tp3.docs

import graf.gremlin.structure.util.TinkerGraphFactory
import graf.gremlin.structure.util.show._
import graf.gremlin.structure.convert.decorateAll._
import scalaz._
import Scalaz._

object GraphVariables extends App {
  def ==>(e: Any): Unit = println("==>" + e)

  val graph = TinkerGraphFactory.open()
  graph.println

  graph.variables.println

  graph.variables.set("systemAdmins", List("stephen", "peter", "pavel"))
  graph.variables.set("systemUsers", List("matthias", "marko", "josh"))
  println(graph.variables.keys.asScala)
  println(graph.variables.get("systemUsers").asScala)
  graph.variables.get("systemUsers").asScala.map { (xs: List[String]) ⇒ xs.foreach(==>); xs }
  graph.variables.remove("systemAdmins")
  println(graph.variables.keys.asScala)
}
