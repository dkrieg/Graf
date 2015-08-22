package com.graf.gremlin
package structure

import scala.collection.JavaConversions._

trait GrafElement[E <: Element] extends Product with Serializable {

  def graph: GrafGraph = e.graph().asScala

  def id[A]: A = e.id().asInstanceOf[A]

  def label: String = e.label()

  def keys: Set[String] = e.keys().toSet

  def value[V](key: String): V = e.value(key)

  def values: Iterator[Any] = e.values().toIterator

  def values(keys: Seq[String]): Iterator[Any] = e.values(keys: _*).toIterator

  def property[V](key: String, value: V): GrafProperty[V] = e.property(key, value).asScala

  def property[V](key: String): GrafProperty[V] = e.property[V](key).asScala

  def properties[P <: GrafProperty[Any]]: Iterator[GrafProperty[Any]] = e.properties[Any]().toIterator.asScala

  def properties[P <: GrafProperty[Any]](keys: Seq[String]): Iterator[GrafProperty[Any]] = e.properties[Any](keys: _*).toIterator.asScala

  def remove(): Unit = e.remove()

  private[structure] def e: E
}
