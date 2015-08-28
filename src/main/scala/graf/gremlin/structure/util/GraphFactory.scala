package graf.gremlin
package structure.util

import graf.gremlin.structure.convert.wrapAll._
import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory._
import org.apache.tinkerpop.gremlin.tinkergraph.structure.{TinkerFactory, TinkerGraph}

trait GraphFactory {
  def apply(c: Configuration): Graph = open(c)

  def apply(s: String): Graph = open(s)

  def apply(m: Map[String, Any]): Graph = open(m)

}

object GraphFactory extends GraphFactory

object TinkerGraphFactory {
  def open(): Graph = GraphFactory {
    Map(GRAPH -> classOf[TinkerGraph].getName)
  }

  def createModern(): Graph = TinkerFactory.createModern()
}
