package graf.gremlin
package structure

import org.apache.tinkerpop.gremlin.structure.Transaction.{
  Status ⇒ JStatus,
  Workload ⇒ JWorkload
}
import scala.collection.JavaConversions._

case class GrafTransaction(private[structure] val tx: Transaction) {
  type Status = Status.Status

  object Status extends Enumeration {
    type Status = Value
    val COMMIT, ROLLBACK = Value
  }

  implicit class JStatusAsScala(s: JStatus) {
    def asScala = {
      import JStatus._
      s match {
        case `COMMIT` ⇒ Status.COMMIT
        case `ROLLBACK` ⇒ Status.ROLLBACK
      }
    }
  }

  implicit class StatusAsJava(s: Status) {
    def asJava = {
      import Status._
      s match {
        case `COMMIT` ⇒ JStatus.COMMIT
        case `ROLLBACK` ⇒ JStatus.ROLLBACK
      }
    }
  }

  case class Workload[A](private[structure] val workload: JWorkload[A]) {
    def retry(tries: Int, delay: Long, exceptionsToRetryOn: Set[Class[_]]): A = workload.retry(tries, delay, exceptionsToRetryOn)

    def exponentialBackoff(): A = workload.exponentialBackoff()

    def fireAndForget(): A = workload.fireAndForget()

    def retry(): A = workload.retry()

    def retry(tries: Int, delay: Long): A = workload.retry(tries, delay)

    def retry(tries: Int): A = workload.retry(tries)

    def exponentialBackoff(tries: Int, initialDelay: Long): A = workload.exponentialBackoff(tries, initialDelay)

    def oneAndDone(): A = workload.oneAndDone()

    def attempt[G <: Graph](retryStrategy: (GrafGraph, GrafGraph ⇒ A) ⇒ A): A = workload.attempt {
      import java.util.function.{ Function ⇒ JFunction }
      (g: Graph, f: JFunction[Graph, A]) ⇒ retryStrategy(g, (gg: GrafGraph) ⇒ f(gg))
    }

    def exponentialBackoff(tries: Int, initialDelay: Long, exceptionsToRetryOn: Set[Class[_]]): A =
      workload.exponentialBackoff(tries, initialDelay, exceptionsToRetryOn)

    def exponentialBackoff(tries: Int): A = workload.exponentialBackoff(tries)
  }

  def removeTransactionListener(f: Status ⇒ Unit): Unit = tx.removeTransactionListener((s: JStatus) ⇒ f(s.asScala))

  def isOpen: Boolean = tx.isOpen

  def commit(): Unit = tx.commit()

  def onClose(f: GrafTransaction ⇒ Unit): GrafTransaction = tx.onClose((t: Transaction) ⇒ f(t))

  def rollback(): Unit = tx.rollback()

  def submit[A](work: GrafGraph ⇒ A): Workload[A] =
    Workload(tx.submit((g: Graph) ⇒ work(g)))

  def addTransactionListener(f: Status ⇒ Unit): Unit = tx.addTransactionListener((s: JStatus) ⇒ f(s.asScala))

  def onReadWrite(f: GrafTransaction ⇒ Unit): GrafTransaction = tx.onReadWrite((tx: Transaction) ⇒ f(tx))

  def open(): Unit = tx.open()

  def clearTransactionListeners(): Unit = tx.clearTransactionListeners()

  def close(): Unit = tx.close()

  def createThreadedTx[G <: Graph](): GrafGraph = tx.createThreadedTx()
}
