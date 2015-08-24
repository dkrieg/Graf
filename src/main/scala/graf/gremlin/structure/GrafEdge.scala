package graf.gremlin
package structure

import scala.collection.JavaConversions._

case class GrafEdge(private[structure] override val e: Edge) extends GrafElement[Edge] {

  def vertices(direction: Direction): Iterator[GrafVertex] = e.vertices(direction).toIterator

  def outVertex: GrafVertex = e.outVertex()

  def bothVertices: Iterator[GrafVertex] = e.bothVertices().toIterator

  def inVertex: GrafVertex = e.inVertex()
}
