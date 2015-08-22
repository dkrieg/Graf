package com.graf.gremlin
package structure

import com.graf.gremlin.structure.schema.{ Atom, Label }

import scala.collection.JavaConversions._

case class GrafVertex(private[structure] override val e: Vertex) extends GrafElement[Vertex] {
  def addEdge(label: String, inVertex: GrafVertex) = {
    e.addEdge(label, inVertex.asJava).asScala
  }

  def addEdge(label: Label, inVertex: GrafVertex, atoms: Atom*) = e.addEdge(
    label.value.asInstanceOf[String],
    inVertex.asJava,
    atoms flatMap { atom ⇒ Seq(atom.key, atom.value) }: _*).asScala

  override def property[A](key: String, value: A): GrafVertexProperty[A] = e.property(key, value).asScala

  def property[A](key: String, value: A, keyValues: (String, Any)*): GrafVertexProperty[A] =
    e.property(key, value, keyValues: _*).asScala

  override def property[A](key: String): GrafVertexProperty[A] = e.property[A](key).asScala

  def property[A](c: Cardinality, key: String, value: A, p: (String, Any)*): GrafVertexProperty[A] = {
    val xs = p.foldLeft(Seq[Any]()) { (xs, x) ⇒ x._1 +: x._2 +: xs }
    e.property(c.asJava, key, value, xs map (_.asInstanceOf[Object]): _*).asScala
  }

  override def properties[P <: GrafProperty[Any]]: Iterator[GrafVertexProperty[Any]] =
    e.properties[Any]().toIterator.asScala

  override def properties[P <: GrafProperty[Any]](keys: Seq[String]): Iterator[GrafVertexProperty[Any]] =
    e.properties[Any](keys: _*).toIterator.asScala

  def vertices(direction: Direction, edgeLabels: String*): Iterator[GrafVertex] =
    e.vertices(direction.asJava, edgeLabels: _*).toIterator.asScala

  def edges(direction: Direction, edgeLabels: String*): Iterator[GrafEdge] =
    e.edges(direction.asJava, edgeLabels: _*).toIterator.asScala

  def <--(se: SemiEdge) = se.from.addEdge(se.label, this, se.atoms:_*)

  def <--(de: SemiDoubleEdge): (GrafEdge, GrafEdge) =
    addEdge(de.label, de.right, de.atoms:_*) -> de.right.addEdge(de.label, this, de.atoms:_*)

  def ---(label: String) = SemiEdge(Label(label), this)

  def ---(label: Label, atoms: Atom*) = SemiEdge(label, this, atoms:_*)
}
