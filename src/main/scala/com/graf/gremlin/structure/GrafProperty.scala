package com.graf.gremlin
package structure

class GrafProperty[A](private[structure] val property: Property[A]) {
  def isPresent: Boolean = property.isPresent

  def ifPresent(consumer: A ⇒ Unit): Unit = property.ifPresent(consumer)

  def key: String = property.key()

  def value: A = property.value()

  def element = property.element().asScala

  def orElse(otherValue: A): A = property.orElse(otherValue)

  def orElseGet(f: ⇒ A): A = property.orElseGet(f)

  def orElseThrow[E <: Throwable](f: ⇒ E): A = property.orElseThrow(f)

  def remove(): Unit = property.remove()
}

object GrafProperty {
  def apply[A](property: Property[A]) = new GrafProperty(property)
}