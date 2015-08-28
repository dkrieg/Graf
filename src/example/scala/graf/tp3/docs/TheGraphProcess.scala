package graf.tp3.docs

import graf.gremlin._
import graf.gremlin.process.traversal.dsl.graph._
import graf.gremlin.structure.util._
import graf.gremlin.structure.util.show._

import scala.language.postfixOps
import scalaz.Scalaz._

object TheGraphProcess extends App {

  val graph = TinkerGraphFactory.createModern()
  graph.println

  val g = graph.traversal(grafBuilder)
  println(g)

  g.V.has("name", "marko").out("knows").values("name").foreach(println)

  val marko = g.V.has("name", "marko").head
  marko.println

  g.V(marko).out("knows").foreach(println)

  g.V(marko).out("knows").values("name").foreach(println)
}
