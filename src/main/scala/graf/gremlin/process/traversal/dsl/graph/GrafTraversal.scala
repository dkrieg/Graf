package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure.convert.wrapAll._
import graf.gremlin.structure.convert.decorateAll._

class GrafTraversal[Start, End](private[graph] val traversal: Traversal[Start, End]) extends Iterator[End] {
  def head: End = toList.head

  def headOption: Option[End] = toList.headOption

  def iterate[A, B](): GrafTraversal[A, B] = traversal.iterate().asInstanceOf[Traversal[A, B]]

  def forEachRemaining(action: End ⇒ Unit): Unit = traversal.forEachRemaining(action)

  def next(amount: Int): List[End] = traversal.next(amount).toList

  def fill(it: Iterable[End]): Iterable[End] = traversal.fill(it.asJavaCollection).asScala

  def tryNext(): Option[End] = traversal.tryNext()

  def asAdmin(): GrafTraversalAdmin[Start, End] = new GrafTraversalAdmin(traversal.asAdmin())

  override def toList: List[End] = traversal.toList.toList

  def toJStream: JStream[End] = traversal.toStream

  def toBulkSet: BulkSet[End] = traversal.toBulkSet

  def forEachRemaining[E2](endType: Class[E2], f: E2 ⇒ Unit): Unit = traversal.forEachRemaining(endType, f)

  override def toSet[B >: End]: Set[B] = traversal.toSet.toSet

  def remove(): Unit = traversal.remove()

  override def hasNext: Boolean = traversal.hasNext

  override def next(): End = traversal.next()
}
