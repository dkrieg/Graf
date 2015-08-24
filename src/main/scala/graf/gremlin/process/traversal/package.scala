package graf.gremlin.process

import java.util.function.Consumer
import java.util.{ Iterator ⇒ JIterator }

import scala.collection.JavaConversions._
import org.apache.tinkerpop.gremlin.process.traversal.{
  Traverser ⇒ JTraverser,
  TraversalSideEffects ⇒ JTraversalSideEffects,
  Step ⇒ JStep,
  Pop ⇒ JPop,
  P ⇒ JP,
  Scope ⇒ JScope,
  Order ⇒ JOrder,
  Path ⇒ JPath
}
import scala.language.implicitConversions

package object traversal {
  type Traverser[A] = JTraverser[A]
  type TraverserAdmin[A] = JTraverser.Admin[A]
  type TraversalSideEffects = JTraversalSideEffects
  type Step[Start, End] = JStep[Start, End]
  type Pop = JPop
  type P[A] = JP[A]
  type Scope = JScope
  type Order = JOrder
  type Path = JPath

  implicit def toGrafTraverser[A](t: Traverser[A]): GrafTraverser[A] =
    new GrafTraverser[A](t)

  implicit def toTraverser[Start](g: GrafTraverser[Start]): Traverser[Start] = g.traverser

  implicit def toIteratorTraverser[Start](it: Iterator[GrafTraverser[Start]]): JIterator[Traverser[Start]] =
    it.map(_.traverser)

  implicit def toTraverserAdmin[T](g: GrafTraverserAdmin[T]): TraverserAdmin[T] = g.admin
  implicit def toGrafTraverserAdmin[T](g: TraverserAdmin[T]): GrafTraverserAdmin[T] = new GrafTraverserAdmin[T](g)

  implicit def toGrafTraversalSideEffects(t: TraversalSideEffects): GrafTraversalSideEffects =
    GrafTraversalSideEffects(t)

  implicit def toTraversalSideEffects(t: GrafTraversalSideEffects): TraversalSideEffects = t.traversalSideEffects

  implicit def toStep[Start, End](g: GrafStep[Start, End]): Step[Start, End] = g.step

  implicit def toGrafStep[Start, End](s: Step[Start, End]): GrafStep[Start, End] = GrafStep(s)

  implicit def toConsumerTraverser[A](f: GrafTraverser[A] ⇒ Unit): Consumer[JTraverser[A]] = new Consumer[JTraverser[A]] {
    override def accept(t: JTraverser[A]): Unit = f(t)
  }
}
