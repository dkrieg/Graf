package graf.gremlin
package structure.util

import graf.gremlin.structure.convert.wrapAll._
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory._

trait GraphFactory {
  def apply(c: Configuration): Graph = open(c)

  def apply(s: String): Graph = open(s)

  def apply(m: Map[String, Any]): Graph = open(m)

}

object GraphFactory extends GraphFactory

object TinkerGraphFactory {
  def open(): Graph = GraphFactory {
    Map(GRAPH -> "org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph")
  }
}
