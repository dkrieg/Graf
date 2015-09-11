package graf.tp3.docs

import graf.gremlin._
import graf.gremlin.process.traversal.dsl.graph._
import graf.gremlin.structure.util._

import scala.math.Ordering.Int

object TheCrew extends App {
  def ==>(e: Any): Unit = println("==>" + e)

  val graph = TinkerGraphFactory.createTheCrew()
  val g = graph.traversal(grafStandard)
  g.V.as("a").
    properties("location").as("b").
    hasNot("endTime").as("c").
    select("a", "b", "c").by("name").by(_.value).by("startTime").foreach(==>)

  g.V.has("name", "gremlin").inE("uses").
    order.by("skill", Int).as("a").
    outV.as("b").
    select("a", "b").by("skill").by("name").foreach(==>)
}
