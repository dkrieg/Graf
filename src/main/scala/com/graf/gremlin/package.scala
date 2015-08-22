package com.graf

import java.util.Optional
import java.util.function.{ Function ⇒ JFunction, BiFunction, Supplier, Consumer }

import scala.language.implicitConversions

package object gremlin {
  implicit def fnToConsumer[A](f: A ⇒ Unit): Consumer[A] = new Consumer[A] {
    override def accept(a: A): Unit = f(a)
  }

  implicit def fnToSupplier[A](f: ⇒ A): Supplier[A] = new Supplier[A] {
    override def get(): A = f
  }

  implicit def fnToFunc[A, B](f: A ⇒ B): JFunction[A, B] = new JFunction[A, B] {
    override def apply(a: A): B = f(a)
  }

  implicit def funcToFn[A, B](f: JFunction[A, B]): A ⇒ B = a ⇒ f.apply(a)

  implicit def fnToBiFunction[A, B, C](f: (A, B) ⇒ C): BiFunction[A, B, C] = new BiFunction[A, B, C] {
    override def apply(a: A, b: B): C = f(a, b)
  }

  implicit def biFunctionToFn[A, B, C](bif: BiFunction[A, B, C]): (A, B) ⇒ C = (a, b) ⇒ bif.apply(a, b)

  implicit class OptionalAsScala[A](o: Optional[A]) {
    def asScala: Option[A] = if (o.isPresent) Some(o.get()) else None
  }
}
