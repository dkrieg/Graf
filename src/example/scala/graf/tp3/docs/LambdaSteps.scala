package graf.tp3.docs

import graf.gremlin._
import graf.gremlin.structure.util.TinkerGraphFactory
import org.apache.tinkerpop.gremlin.process.traversal.step.TraversalOptionParent.Pick.none

object LambdaSteps extends App {
  def >> = println(">>")
  def ==>(e: Any): Unit = println("==>" + e)

  val graph = TinkerGraphFactory.createModern()
  val g = graph.traversal(grafStandard)

  g.V(1).out.values("name").toList.foreach(==>)
  >>
  g.V(1).out.mapT(_.get.value[String]("name")).foreach(==>)
  >>
  g.V.filterT(_.get.label == "person").foreach(==>)
  >>
  g.V.hasLabel("person").foreach(==>)
  >>
  g.V.hasLabel("person").sideEffectT(==>).iterate()
  >>
  g.V.branch(_.values("name")).
    option("marko", _.start[String].values[String]("age")).
    option(none, _.start[String].values[String]("name")).foreach(==>)
  >>
  g.V.choose(
    _.has("name", "marko"),
    _.start[String].values[String]("age"),
    _.start[String].values[String]("name")).foreach(==>)
}
