package com.graf.gremlin
package structure

case class GrafVertexProperty[A](private[structure] val e: VertexProperty[A])
  extends GrafProperty[A](e) with GrafElement[VertexProperty[A]] {

  override def remove(): Unit = super.remove()
}
