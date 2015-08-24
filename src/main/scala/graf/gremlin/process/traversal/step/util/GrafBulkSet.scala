package graf.gremlin
package process.traversal.step.util

import java.lang.{ Long ⇒ JLong }
import java.util.Spliterator
import java.util.stream.{ Stream ⇒ JStream }

import scala.collection.JavaConversions._

case class GrafBulkSet[S](private[util] val bulkSet: BulkSet[S]) {

  def longSize(): Long = bulkSet.longSize()

  def remove(s: Any): Boolean = bulkSet.remove(s)

  def iterator(): Iterator[S] = bulkSet.iterator()

  override def toString: String = bulkSet.toString

  def spliterator(): Spliterator[S] = bulkSet.spliterator()

  def addAll(collection: Iterable[S]): Boolean = bulkSet.addAll(collection)

  def get(s: S): Long = bulkSet.get(s)

  def contains(s: Any): Boolean = bulkSet.contains(s)

  override def equals(`object`: Any): Boolean = bulkSet.equals(`object`)

  override def hashCode(): Int = bulkSet.hashCode()

  def add(s: S, bulk: Long): Boolean = bulkSet.add(s, bulk)

  def add(s: S): Boolean = bulkSet.add(s)

  def uniqueSize(): Int = bulkSet.uniqueSize()

  def removeAll(collection: Iterable[Any]): Boolean = bulkSet.removeAll(collection)

  def clear(): Unit = bulkSet.clear()

  def forEach(f: (S, Long) ⇒ Unit): Unit = bulkSet.forEach((s: S, n: JLong) ⇒ f(s, n))

  def isEmpty: Boolean = bulkSet.isEmpty

  def size(): Int = bulkSet.size()

  def retainAll(c: Iterable[Any]): Boolean = bulkSet.retainAll(c)

  def containsAll(c: Iterable[Any]): Boolean = bulkSet.containsAll(c)

  def removeIf(filter: S ⇒ Boolean): Boolean = bulkSet.removeIf(filter)

  def parallelStream(): JStream[S] = bulkSet.parallelStream()

  def stream(): JStream[S] = bulkSet.stream()

  def forEach(action: S ⇒ Unit): Unit = bulkSet.forEach(action)
}
