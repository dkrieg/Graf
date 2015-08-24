package graf.gremlin
package structure

import scala.collection.JavaConversions._

trait GrafElement[E <: Element] extends Product with Serializable {

  def graph: GrafGraph = e.graph()

  def id[A]: A = e.id().asInstanceOf[A]

  def label: String = e.label()

  def keys: Set[String] = e.keys().toSet

  def value[V](key: String): V = e.value(key)

  def values: Iterator[Any] = e.values().toIterator

  def values(keys: Seq[String]): Iterator[Any] = e.values(keys: _*).toIterator

  def property[V](key: String, value: V): GrafProperty[V] = e.property(key, value)

  def property[V](key: String): GrafProperty[V] = e.property[V](key)

  def properties: Iterator[GrafProperty[Any]] = e.properties[Any]().toIterator

  def properties(keys: Seq[String]): Iterator[GrafProperty[Any]] = e.properties[Any](keys: _*).toIterator

  def remove(): Unit = e.remove()

  private[structure] def e: E
}
