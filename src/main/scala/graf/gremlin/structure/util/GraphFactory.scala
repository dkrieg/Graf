package graf.gremlin.structure.util

import graf.gremlin.structure._
import org.apache.tinkerpop.gremlin.structure.Graph.GRAPH
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory._

import scala.collection.JavaConversions._

trait GraphFactory {
  def apply(c: Configuration) = open(c).asScala

  def apply(s: String) = open(s).asScala

  def apply(m: Map[String, Any]) = open(m).asScala

}

object GraphFactory extends GraphFactory

object TinkerGraphFactory {
  def open() = GraphFactory {
    Map(GRAPH -> "org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph")
  }
}
