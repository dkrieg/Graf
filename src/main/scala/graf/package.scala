import graf.gremlin.structure._

import scalaz.Free._
import scalaz.Memo._
import scalaz._
import scalaz.concurrent.{Future, Task}

package object graf {
  type Graf[A] = FreeC[GrafOp, A]
  type GrafReader[A] = Graph ⇒ A
  type GrafTask[A] = GrafReader[OneTimeTask[A]]

  sealed trait GrafOp[+A]

  case object GetGraph extends GrafOp[Graph]
  case class Point[A](a: A) extends GrafOp[A]

  case class OneTimeTask[A](override val get: Future[Throwable \/ A]) extends Task[A](get) {
    private val memo = immutableHashMapMemo {
      get: Future[Throwable \/ A] ⇒
        get.run match {
          case -\/(e) ⇒ throw e
          case \/-(a) ⇒ a
        }
    }

    def flatMap[B](f: (A) ⇒ OneTimeTask[B]): OneTimeTask[B] =
      OneTimeTask(get flatMap {
        case -\/(e) ⇒ Future.now(-\/(e))
        case \/-(a) ⇒ Task.Try(f(a)) match {
          case e @ -\/(_) ⇒ Future.now(e)
          case \/-(task) ⇒ task.get
        }
      })

    override def map[B](f: (A) ⇒ B): OneTimeTask[B] = OneTimeTask(get map { _ flatMap { a ⇒ Task.Try(f(a)) } })

    override def run: A = memo(get)
  }

  def G: Graf[Graph] = liftFC(GetGraph)

  implicit val toState: GrafOp ~> GrafTask = new (GrafOp ~> GrafTask) {
    override def apply[A](fa: GrafOp[A]): GrafTask[A] = fa match {
      case GetGraph ⇒ g ⇒ OneTimeTask(Future.delay(\/-(g)))
      case Point(a) ⇒ g ⇒ OneTimeTask(Future.delay(\/-(a)))
    }
  }

  implicit val GrafRMonad = new Monad[GrafTask] {

    override def bind[A, B](fa: GrafTask[A])(f: (A) ⇒ GrafTask[B]): GrafTask[B] = g ⇒ fa(g) flatMap (a ⇒ f(a)(g))

    override def point[A](a: ⇒ A): GrafTask[A] = g ⇒ OneTimeTask(Future.delay(\/-(a)))
  }

  implicit val GrafApplicative = new Applicative[Graf] {
    override def point[A](a: ⇒ A): Graf[A] = liftFC(Point(a))

    override def ap[A, B](fa: ⇒ Graf[A])(f: ⇒ Graf[A ⇒ B]): Graf[B] = for {
      a <- fa
      n <- f
    } yield n(a)

  }

  implicit class GrafFunctions[A](g: Graf[A]) extends GrafTask[A] {
    def bind(graph: Graph) = apply(graph)

    override def apply(graph: Graph): OneTimeTask[A] =
      runFC(g)(toState).apply(graph)
  }
}
