package graf.gremlin.structure.convert

import java.util.{Optional, Comparator}
import java.util.function.{ Function ⇒ JFunction, _ }

import graf.gremlin.structure.convert.WrapAsScala._

import scala.collection.convert
import scala.language.implicitConversions

trait DecorateAsScala extends convert.DecorateAsScala {
  import Decorators._

  implicit def asScalaBiConsumerConverter[T, U](f: BiConsumer[T, U]): AsScala[(T, U) ⇒ Unit] =
    new AsScala(asScalaBiConsumer(f))

  implicit def asScalaBiFunctionConverter[T, U, R](f: BiFunction[T, U, R]): AsScala[(T, U) ⇒ R] =
    new AsScala(asScalaBiFunction(f))

  implicit def asScalaBinaryOperatorConverter[T](f: BinaryOperator[T]): AsScala[(T, T) ⇒ T] =
    new AsScala(asScalaBinaryOperator(f))

  implicit def asScalaBiPredicateConverter[T, U](f: BiPredicate[T, U]): AsScala[(T, U) ⇒ Boolean] =
    new AsScala(asScalaBiPredicate(f))

  implicit def asScalaConsumerConverter[T](f: Consumer[T]): AsScala[T ⇒ Unit] =
    new AsScala(asScalaConsumer(f))

  implicit def asScalaJFunctionConverter[T, U](f: JFunction[T, U]): AsScala[T ⇒ U] =
    new AsScala(asScalaJFunction(f))

  implicit def asScalaPredicateConverter[T](f: Predicate[T]): AsScala[T ⇒ Boolean] =
    new AsScala(asScalaPredicate(f))

  implicit def asScalaSupplierConverter[T](f: Supplier[T]): AsScala[Unit ⇒ T] =
    new AsScala(asScalaSupplier(f))

  implicit def asScalaUnaryOperatorConverter[T](f: UnaryOperator[T]): AsScala[T ⇒ T] =
    new AsScala(asScalaUnaryOperator(f))

  implicit def asScalaComparatorConverter[T](f: Comparator[T]): AsScala[Ordering[T]] =
    new AsScala(asScalaComparator(f))

  implicit def asScalaOptionConverter[T](f: Optional[T]): AsScala[Option[T]] =
    new AsScala(asScalaOption(f))
}
