package graf.gremlin.structure.convert

import java.util.{Optional, Comparator}
import java.util.function.{ Function ⇒ JFunction, _ }

import scala.collection.convert
import scala.language.implicitConversions

trait WrapAsJava extends convert.WrapAsJava {
  import Wrappers._

  implicit def asJavaBiConsumer[T, U](f: (T, U) ⇒ Unit): BiConsumer[T, U] = f match {
    case BiConsumerWrapper(wrapped) ⇒ wrapped.asInstanceOf[BiConsumer[T, U]]
    case _ ⇒ BiConsumerFunctionWrapper(f)
  }

  implicit def asJavaBiFunction[T, U, R](f: (T, U) ⇒ R): BiFunction[T, U, R] = f match {
    case BiFunctionWrapper(wrapped) ⇒ wrapped.asInstanceOf[BiFunction[T, U, R]]
    case _ ⇒ BiFunctionFunctionWrapper(f)
  }

  implicit def asJavaBinaryOperator[T](f: (T, T) ⇒ T): BinaryOperator[T] = f match {
    case BinaryOperatorWrapper(wrapped) ⇒ wrapped.asInstanceOf[BinaryOperator[T]]
    case _ ⇒ BinaryOperatorFunctionWrapper(f)
  }

  implicit def asJavaBiPredicate[T, U](f: (T, U) ⇒ Boolean): BiPredicate[T, U] = f match {
    case BiPredicateWrapper(wrapped) => wrapped.asInstanceOf[BiPredicate[T, U]]
    case _ => BiPredicateFunctionWrapper(f)
  }

  implicit def asJavaConsumer[T](f: T ⇒ Unit): Consumer[T] = f match {
    case ConsumerWrapper(wrapped) => wrapped.asInstanceOf[Consumer[T]]
    case _ => ConsumerFunctionWrapper(f)
  }

  implicit def asJavaJFunction[T, U](f: T ⇒ U): JFunction[T, U] = f match {
    case JFunctionWrapper(wrapped) => wrapped.asInstanceOf[JFunction[T, U]]
    case _ => JFunctionFunctionWrapper(f)
  }

  implicit def asJavaPredicate[T](f: T ⇒ Boolean): Predicate[T] = f match {
    case PredicateWrapper(wrapped) => wrapped.asInstanceOf[Predicate[T]]
    case _ => PredicateFunctionWrapper(f)
  }

  implicit def asJavaSupplier[T](f: Unit ⇒ T): Supplier[T] = f match {
    case SupplierWrapper(wrapped) ⇒ wrapped.asInstanceOf[Supplier[T]]
    case _ ⇒ SupplierFunctionWrapper(f)
  }

  implicit def asJavaUnaryOperator[T](f: T ⇒ T): UnaryOperator[T] = f match {
    case UnaryOperatorWrapper(wrapped) ⇒ wrapped.asInstanceOf[UnaryOperator[T]]
    case _ ⇒ UnaryOperatorFunctionWrapper(f)
  }

  implicit def asJavaComparator[T](f: Ordering[T]): Comparator[T] = f match {
    case ComparatorWrapper(wrapped) ⇒ wrapped
    case _ ⇒ OrderingWrapper(f)
  }

  implicit def asJavaOptional[T](f: Option[T]): Optional[T] = f match {
    case Some(t) => Optional.of(t)
    case None => Optional.empty()
  }
}

object WrapAsJava extends WrapAsJava