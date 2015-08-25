package graf.gremlin.structure.convert

import java.util.Comparator
import java.util.function.{ Function ⇒ JFunction, _ }

trait Wrappers {
  case class BiConsumerWrapper[T, U](underlying: BiConsumer[T, U]) extends ((T, U) ⇒ Unit) {
    def apply(t: T, u: U): Unit = underlying.accept(t, u)
  }
  case class BiConsumerFunctionWrapper[T, U](underlying: (T, U) ⇒ Unit) extends BiConsumer[T, U] {
    override def accept(t: T, u: U): Unit = underlying(t, u)
  }

  case class BiFunctionWrapper[T, U, R](underlying: BiFunction[T, U, R]) extends ((T, U) ⇒ R) {
    def apply(t: T, u: U): R = underlying(t, u)
  }
  case class BiFunctionFunctionWrapper[T, U, R](underlying: (T, U) ⇒ R) extends BiFunction[T, U, R] {
    def apply(t: T, u: U): R = underlying(t, u)
  }

  case class BinaryOperatorWrapper[T](underlying: BinaryOperator[T]) extends ((T, T) ⇒ T) {
    def apply(v1: T, v2: T): T = underlying(v1, v2)
  }
  case class BinaryOperatorFunctionWrapper[T](underlying: (T, T) ⇒ T) extends BinaryOperator[T] {
    def apply(t: T, u: T): T = underlying(t, u)
  }

  case class BiPredicateWrapper[T, U](underlying: BiPredicate[T, U]) extends ((T, U) ⇒ Boolean) {
    def apply(v1: T, v2: U): Boolean = underlying.test(v1, v2)
  }
  case class BiPredicateFunctionWrapper[T, U](underlying: (T, U) ⇒ Boolean) extends BiPredicate[T, U] {
    override def test(t: T, u: U): Boolean = underlying(t, u)
  }

  case class ConsumerWrapper[T](underlying: Consumer[T]) extends (T ⇒ Unit) {
    def apply(v1: T): Unit = underlying.accept(v1)
  }
  case class ConsumerFunctionWrapper[T](underlying: T ⇒ Unit) extends Consumer[T] {
    override def accept(t: T): Unit = underlying(t)
  }

  case class JFunctionWrapper[T, R](underlying: JFunction[T, R]) extends (T ⇒ R) {
    override def apply(v1: T): R = underlying(v1)
  }
  case class JFunctionFunctionWrapper[T, R](underlying: T ⇒ R) extends JFunction[T, R] {
    override def apply(v1: T): R = underlying(v1)
  }

  case class PredicateWrapper[T](underlying: Predicate[T]) extends (T ⇒ Boolean) {
    override def apply(v1: T): Boolean = underlying.test(v1)
  }
  case class PredicateFunctionWrapper[T](underlying: T ⇒ Boolean) extends Predicate[T] {
    override def test(v1: T): Boolean = underlying(v1)
  }

  case class SupplierWrapper[T](underlying: Supplier[T]) extends (Unit ⇒ T) {
    override def apply(v1: Unit): T = underlying.get()
  }
  case class SupplierFunctionWrapper[T](underlying: Unit ⇒ T) extends Supplier[T] {
    override def get(): T = underlying(Unit)
  }

  case class UnaryOperatorWrapper[T](underlying: UnaryOperator[T]) extends (T ⇒ T) {
    override def apply(v1: T): T = underlying(v1)
  }
  case class UnaryOperatorFunctionWrapper[T](underlying: T ⇒ T) extends UnaryOperator[T] {
    override def apply(v1: T): T = underlying(v1)
  }

  case class ComparatorWrapper[T](underlying: Comparator[T]) extends Ordering[T] {
    override def compare(x: T, y: T): Int = underlying.compare(x, y)
  }
  case class OrderingWrapper[T](underlying: Ordering[T]) extends Comparator[T] {
    override def compare(x: T, y: T): Int = underlying.compare(x, y)
  }
}

object Wrappers extends Wrappers with Serializable
