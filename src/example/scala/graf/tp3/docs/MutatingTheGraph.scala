package graf.tp3.docs

import graf.gremlin.structure.convert.wrapAll._
import graf.gremlin.structure.schema._
import graf.gremlin.structure.syntax._
import graf.gremlin.structure.util._
import graf.gremlin.structure.util.show._
import org.apache.tinkerpop.gremlin.structure.VertexProperty

import scala.language.postfixOps
import scalaz.Scalaz._

object MutatingTheGraph extends App {
  val Software = Label("software")
  val DependsOn = Label("dependsOn")
  val Encapsulates = Label("encapsulates")
  val Name = Key[String]("name")
  val Created = Key[Int]("created")

  val g = TinkerGraphFactory.open()

  // add a software vertex with a name property
  val gremlin = g + (Software, Name("gremlin"))
  gremlin.println

  // only one vertex should exist
  assert(g.vertices().toList.size == 1)

  // no edges should exist as none have been created
  assert(g.edges().toList.isEmpty)

  // add a new property
  val vp0: VertexProperty[Int] = gremlin.property(Created(2009))
  vp0.println

  // add a new software vertex to the graph
  val blueprints = g + (Software, Name("blueprints"))
  blueprints.println

  // connect gremlin to blueprints via a dependsOn-edge
  gremlin --- DependsOn --> blueprints println

  // now there are two vertices and one edge
  assert(g.vertices().toList.size == 2)
  assert(g.edges().toList.size == 1)

  // add a property to blueprints
  val vp1: VertexProperty[Int] = blueprints.property(Created(2010))
  vp1.println

  // remove that property
  blueprints.property(Created).remove().println

  // connect gremlin to blueprints via encapsulates
  gremlin --- Encapsulates --> blueprints println

  assert(g.vertices().toList.size == 2)
  assert(g.edges().toList.size == 2)

  // removing a vertex removes all its incident edges as well
  blueprints.remove().println
  gremlin.remove().println

  // the graph is now empty
  assert(g.vertices().toList.isEmpty)
  assert(g.edges().toList.isEmpty)
  g.close()

  // tada!
}
