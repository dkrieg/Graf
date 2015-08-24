package graf.gremlin
package process.traversal.dsl.graph

import java.util.stream.Stream

import graf.gremlin.process.traversal.step.util._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class GrafElementGraphTraversal[S, E](private[graph] val traversal: GraphTraversal[S, E]) {


  // Iterator and Traversal functions

  def toSet: Set[E] = traversal.toSet.toSet

  def forEachRemaining[E2](endType: Class[E2], f: E2 => Unit): Unit = traversal.forEachRemaining(endType, f)

  def fill(iterable: Iterable[E]): Iterable[E] = traversal.fill(iterable.asJavaCollection).toIterable

  def forEachRemaining(action: E ⇒ Unit): Unit = traversal.forEachRemaining(action)

  def toBulkSet: GrafBulkSet[E] = traversal.toBulkSet

  def toStream: Stream[E] = traversal.toStream

  def tryNext(): Option[E] = traversal.tryNext().asScala

  def toList: List[E] = traversal.toList.toList

  def next(amount: Int): List[E] = traversal.next(amount).toList

  def next: E = traversal.next()

  def hasNext: Boolean = traversal.hasNext

  def remove(): Unit = traversal.remove()
}
