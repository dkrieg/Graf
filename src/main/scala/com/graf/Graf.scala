package com.graf

import gremlin.scala._

import scalaz._
import scalaz.Memo._
import scalaz.concurrent.Future

object Graf {

  private class GrafMemo[A](action: ⇒ GrafM[A]) extends (ScalaGraph ⇒ OneTimeTask[A]) {
    val memo = weakHashMapMemo[ScalaGraph, OneTimeTask[A]] {
      G: ScalaGraph ⇒
        action.runM {
          case GrafExec(run) ⇒ new OneTimeTask(Future.delay(\/-(run(G))))
        }
    }

    def apply(g: ScalaGraph): OneTimeTask[A] = memo(g)
  }

  def apply[A](action: ⇒ GrafM[A]): Graf[OneTimeTask[A]] =
    Reader[ScalaGraph, OneTimeTask[A]] {
      ???
    }
}

