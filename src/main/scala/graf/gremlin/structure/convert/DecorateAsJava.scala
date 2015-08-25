package graf.gremlin.structure.convert

import java.util.{Optional, Comparator}
import java.util.function.{ Function ⇒ JFunction, _ }

import WrapAsJava._
import scala.collection.convert
import scala.language.implicitConversions

trait DecorateAsJava extends convert.DecorateAsJava {
  import Decorators._

  implicit def asJavaBiConsumerConverter[T, U](f: (T, U) ⇒ Unit): AsJava[BiConsumer[T, U]] =
    new AsJava(asJavaBiConsumer(f))

  implicit def asJavaBiFunctionConverter[T, U, R](f: (T, U) ⇒ R): AsJava[BiFunction[T, U, R]] =
    new AsJava(asJavaBiFunction(f))

  implicit def asJavaBinaryOperatorConverter[T](f: (T, T) ⇒ T): AsJava[BinaryOperator[T]] =
    new AsJava(asJavaBinaryOperator(f))

  implicit def asJavaBiPredicateConverter[T, U](f: (T, U) ⇒ Boolean): AsJava[BiPredicate[T, U]] =
    new AsJava(asJavaBiPredicate(f))

  implicit def asJavaConsumerConverter[T](f: T ⇒ Unit): AsJava[Consumer[T]] =
    new AsJava(asJavaConsumer(f))

  implicit def asJavaJFunctionConverter[T, U](f: T ⇒ U): AsJava[JFunction[T, U]] =
    new AsJava(asJavaJFunction(f))

  implicit def asJavaPredicateConverter[T](f: T ⇒ Boolean): AsJava[Predicate[T]] =
    new AsJava(asJavaPredicate(f))

  implicit def asJavaSupplierConverter[T](f: Unit ⇒ T): AsJava[Supplier[T]] =
    new AsJava(asJavaSupplier(f))

  implicit def asJavaUnaryOperatorConverter[T](f: T ⇒ T): AsJava[UnaryOperator[T]] =
    new AsJava(asJavaUnaryOperator(f))

  implicit def asJavaComparatorConverter[T](f: Ordering[T]): AsJava[Comparator[T]] =
    new AsJava(asJavaComparator(f))

  implicit def asJavaOptionalConverter[T](f: Option[T]): AsJava[Optional[T]] =
    new AsJava(asJavaOptional(f))
}
