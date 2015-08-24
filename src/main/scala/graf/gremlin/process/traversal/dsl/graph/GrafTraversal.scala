package graf.gremlin
package process.traversal
package dsl.graph

import java.util.stream.{Stream => JStream}

import graf.gremlin.process.traversal.step.util._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class GrafTraversal[Start, End](private[graph] val traversal: Traversal[Start, End]) extends Iterator[End] {

  def iterate[A, B](): GrafTraversal[A, B] = traversal.iterate().asInstanceOf[Traversal[A, B]]

  def forEachRemaining(action: End ⇒ Unit): Unit = traversal.forEachRemaining(action)

  def next(amount: Int): List[End] = traversal.next(amount).toList

  def fill(it: Iterable[End]): Iterable[End] = traversal.fill(it.asJavaCollection).toIterable

  def tryNext(): Option[End] = traversal.tryNext()

  def asAdmin(): GrafTraversalAdmin[Start, End] = new GrafTraversalAdmin(traversal.asAdmin())

  override def toList: List[End] = traversal.toList.toList

  def toJStream: JStream[End] = traversal.toStream

  def toBulkSet: GrafBulkSet[End] = traversal.toBulkSet

  def forEachRemaining[E2](endType: Class[E2], f: E2 ⇒ Unit): Unit = traversal.forEachRemaining(endType, f)

  override def toSet[B >: End]: Set[B] = traversal.toSet.toSet

  def remove(): Unit = traversal.remove()

  override def hasNext: Boolean = traversal.hasNext

  override def next(): End = traversal.next()
}
