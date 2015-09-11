package graf.tp3.docs

import graf.gremlin._
import graf.gremlin.process.traversal.dsl.graph._
import graf.gremlin.structure.convert.decorateAll._
import graf.gremlin.structure.util.TinkerGraphFactory
import org.apache.tinkerpop.gremlin.process.traversal.P.neq

object AddEdgeSteps extends App {
  def >> = println(">>")

  def ==>(e: Any): Unit = println("==>" + e)

  val graph = TinkerGraphFactory.createModern()
  val g = graph.traversal(grafStandard)

  g.V(1).as("a").out("created").in("created").
    where(neq("a")).addOutE("co-developer", "a", "year" -> 2009).foreach(==>)
  >>
  g.withSideEffect("a", g.V(3, 5).toList.asJavaCollection).V(4).addInE("createdBy", "a").foreach(==>)
  >>
  g.V.as("a").out("created").as("b").
    select("a", "b").addOutE("b", "createdBy", "a", "acl" -> "public").foreach(==>)
  >>
  g.V(1).as("a").out("knows").addInE("livesNear", "a", "year" -> 2009).inV.inE("livesNear").values("year").foreach(==>)
}
