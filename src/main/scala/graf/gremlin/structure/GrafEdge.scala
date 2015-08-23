package graf.gremlin
package structure

import scala.collection.JavaConversions._

case class GrafEdge(private[structure] override val e: Edge) extends GrafElement[Edge] {

  def vertices(direction: Direction) = e.vertices(direction.asJava).toIterator.asScala

  def outV = e.outVertex().asScala

  def bothV = e.bothVertices().toIterator.asScala

  def inV = e.inVertex().asScala
}
