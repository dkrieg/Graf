package graf.gremlin
package structure

import graf.gremlin.structure.schema.{ Atom, Label }

import scala.collection.JavaConversions._

case class GrafVertex(private[structure] override val e: Vertex) extends GrafElement[Vertex] {
  def addEdge(label: String, inVertex: GrafVertex): GrafEdge =
    e.addEdge(label, inVertex)

  def addEdge(label: Label, inVertex: GrafVertex, atoms: Atom*): GrafEdge = e.addEdge(
    label.value.asInstanceOf[String],
    inVertex,
    atoms flatMap { atom ⇒ Seq(atom.key, atom.value) }: _*)

  override def property[A](key: String, value: A): GrafVertexProperty[A] = e.property(key, value)

  def property[A](key: String, value: A, keyValues: (String, Any)*): GrafVertexProperty[A] =
    e.property(key, value, keyValues: _*)

  override def property[A](key: String): GrafVertexProperty[A] = e.property[A](key)

  def property[A](c: Cardinality, key: String, value: A, p: (String, Any)*): GrafVertexProperty[A] = {
    val xs = p.foldLeft(Seq[Any]()) { (xs, x) ⇒ x._1 +: x._2 +: xs }
    e.property(c, key, value, xs map (_.asInstanceOf[Object]): _*)
  }

  override def properties: Iterator[GrafVertexProperty[Any]] =
    e.properties[Any]().toIterator

  override def properties(keys: Seq[String]): Iterator[GrafVertexProperty[Any]] =
    e.properties[Any](keys: _*).toIterator

  def vertices(direction: Direction, edgeLabels: String*): Iterator[GrafVertex] =
    e.vertices(direction, edgeLabels: _*).toIterator

  def edges(direction: Direction, edgeLabels: String*): Iterator[GrafEdge] =
    e.edges(direction, edgeLabels: _*).toIterator
}
