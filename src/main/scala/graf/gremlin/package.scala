package graf

import java.util.Optional
import java.util.function.{ Function ⇒ JFunction, _ }

import scala.language.implicitConversions

package object gremlin {
  implicit def fnToConsumer[A](f: A ⇒ Unit): Consumer[A] = new Consumer[A] {
    override def accept(a: A): Unit = f(a)
  }

  implicit def fnToSupplier[A](f: ⇒ A): Supplier[A] = new Supplier[A] {
    override def get(): A = f
  }

  implicit def supplierToFn[A](f: Supplier[A]): Unit ⇒ A = Unit ⇒ f.get()

  implicit def fnToFunc[A, B](f: A ⇒ B): JFunction[A, B] = new JFunction[A, B] {
    override def apply(a: A): B = f(a)
  }

  implicit def funcToFn[A, B](f: JFunction[A, B]): A ⇒ B = a ⇒ f.apply(a)

  implicit def fnToBiFunction[A, B, C](f: (A, B) ⇒ C): BiFunction[A, B, C] = new BiFunction[A, B, C] {
    override def apply(a: A, b: B): C = f(a, b)
  }

  implicit def biFunctionToFn[A, B, C](bif: BiFunction[A, B, C]): (A, B) ⇒ C = (a, b) ⇒ bif.apply(a, b)

  implicit def fnToBiConsumer[A, B](f: (A, B) ⇒ Unit): BiConsumer[A, B] = new BiConsumer[A, B] {
    override def accept(a: A, b: B): Unit = f(a, b)
  }

  implicit def biConsumerToFn[A, B](f: BiConsumer[A, B]): (A, B) ⇒ Unit = (a, b) ⇒ f.accept(a, b)

  implicit def fnToPredicate[A](f: A ⇒ Boolean): Predicate[A] = new Predicate[A] {
    override def test(a: A): Boolean = f(a)
  }

  implicit def unaryOpAsFn[A](f: UnaryOperator[A]): A ⇒ A = a ⇒ f(a)

  implicit def fnToUnaryOp[A](f: A ⇒ A): UnaryOperator[A] = new UnaryOperator[A] {
    override def apply(a: A): A = a
  }

  implicit class OptionalAsScala[A](o: Optional[A]) {
    def asScala: Option[A] = o
  }

  implicit def toOption[A](o: Optional[A]): Option[A] = if (o.isPresent) Some(o.get()) else None

  implicit def toOptional[A](o: Option[A]): Optional[A] = if (o.isDefined) Optional.of(o.get) else Optional.empty()
}
