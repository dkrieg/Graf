package graf.gremlin.structure.convert

import java.util.{Optional, Comparator}
import java.util.function.{ Function ⇒ JFunction, _ }

import scala.collection.convert
import scala.language.implicitConversions

trait WrapAsScala extends convert.WrapAsScala {
  import Wrappers._

  implicit def asScalaBiConsumer[T, U](f: BiConsumer[T, U]): (T, U) ⇒ Unit = f match {
    case BiConsumerFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ BiConsumerWrapper(f)
  }

  implicit def asScalaBiFunction[T, U, R](f: BiFunction[T, U, R]): (T, U) ⇒ R = f match {
    case BiFunctionFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ BiFunctionWrapper(f)
  }

  implicit def asScalaBinaryOperator[T](f: BinaryOperator[T]): (T, T) ⇒ T = f match {
    case BinaryOperatorFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ BinaryOperatorWrapper(f)
  }

  implicit def asScalaBiPredicate[T, U](f: BiPredicate[T, U]): (T, U) ⇒ Boolean = f match {
    case BiPredicateFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ BiPredicateWrapper(f)
  }

  implicit def asScalaConsumer[T](f: Consumer[T]): T ⇒ Unit = f match {
    case ConsumerFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ ConsumerWrapper(f)
  }

  implicit def asScalaJFunction[T, U](f: JFunction[T, U]): T ⇒ U = f match {
    case JFunctionFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ JFunctionWrapper(f)
  }

  implicit def asScalaPredicate[T](f: Predicate[T]): T ⇒ Boolean = f match {
    case PredicateFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ PredicateWrapper(f)
  }

  implicit def asScalaSupplier[T](f: Supplier[T]): Unit ⇒ T = f match {
    case SupplierFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ SupplierWrapper(f)
  }

  implicit def asScalaUnaryOperator[T](f: UnaryOperator[T]): T ⇒ T = f match {
    case UnaryOperatorFunctionWrapper(wrapped) ⇒ wrapped
    case _ ⇒ UnaryOperatorWrapper(f)
  }

  implicit def asScalaComparator[T](f: Comparator[T]): Ordering[T] = f match {
    case OrderingWrapper(wrapped) ⇒ wrapped
    case _ ⇒ ComparatorWrapper(f)
  }

  implicit def asScalaOption[T](f: Optional[T]): Option[T] =
    if(f.isPresent) Some(f.get())
    else None
}
object WrapAsScala extends WrapAsScala
