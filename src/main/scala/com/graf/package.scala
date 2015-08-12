package com

import gremlin.scala._

import scalaz.Free._
import scalaz.Memo._
import scalaz._
import scalaz.concurrent.{ Future, Task }

package object graf {
  type Graf[A] = Reader[ScalaGraph, A]
  type GrafM[A] = Free[GrafOp, A]

  sealed trait GrafOp[+A]

  case class GrafExec[+A](run: ScalaGraph ⇒ A) extends (ScalaGraph => A) with GrafOp[A] {
    val memo = weakHashMapMemo {
      g: ScalaGraph => run(g)
    }
    override def apply(g: ScalaGraph): A = {
      println(g.hashCode())
      memo(g)
    }
  }

  def G: GrafM[ScalaGraph] = liftF(GrafExec(identity))

  class OneTimeTask[A](override val get: Future[Throwable \/ A]) extends Task[A](get) {
    val memo = immutableHashMapMemo {
      get: Future[Throwable \/ A] ⇒
        get.run match {
          case -\/(e) ⇒ throw e
          case \/-(a) ⇒ a
        }
    }

    override def run: A = memo(get)
  }

  implicit object GrafOpsFunctor extends Functor[GrafOp] {
    override def map[A, B](fa: GrafOp[A])(f: (A) ⇒ B): GrafOp[B] = fa match {
      case GrafExec(run) ⇒ GrafExec(run andThen f)
    }
  }

  implicit object OneTimeTaskMonad extends Monad[OneTimeTask] {
    override def point[A](a: ⇒ A): OneTimeTask[A] = new OneTimeTask[A](Future.delay(\/-(a)))

    override def bind[A, B](fa: OneTimeTask[A])(f: (A) ⇒ OneTimeTask[B]): OneTimeTask[B] = f(fa.run)
  }

  implicit class GrafOps[A](g: Graf[A]) {
    def exec(sg: ScalaGraph) = g(sg)
  }

  implicit class ScalaGraphOps(graph: ScalaGraph) {
    def ++(properties: (String, Any)*): ScalaVertex = graph.addVertex(properties.toMap)
  }

  implicit val VertexShow = new Show[Vertex] {
    override def shows(f: Vertex): String = {
      val name = f.property("name")
      if (name.isPresent) s"v[${f.id}:${f.label}:${name.value}]"
      else s"v[${f.id}:${f.label}]"
    }
  }

  implicit val EdgeShow = new Show[Edge] {
    override def shows(f: Edge): String = {
      val name = f.property("name")
      val es = if (name.isPresent) s"e[${f.id}:${f.label}:${name.value}]"
      else s"e[${f.id}:${f.label}]"
      VertexShow.shows(f.outVertex) + s" -- $es -> " + VertexShow.shows(f.inVertex)
    }
  }
}
