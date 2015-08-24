package graf.gremlin
package structure

case class GrafVertexProperty[A](private[structure] override val e: VertexProperty[A])
  extends GrafProperty[A](e) with GrafElement[VertexProperty[A]] {

  override def remove(): Unit = super.remove()
}
