package graf.gremlin.structure

import org.apache.tinkerpop.gremlin.structure.util.{ Attachable ⇒ JAttachable }

import scala.language.implicitConversions

package object util {
  type Attachable[A] = JAttachable[A]

  implicit class MappableAttachable[A](at: Attachable[A]) {
    def map[B](f: A ⇒ B): Attachable[B] = new Attachable[B] {
      override def get(): B = f(at.get)
    }
  }

  implicit def toAttachableGrafVertex(at: Attachable[Vertex]): Attachable[GrafVertex] = at map { v ⇒
    val gv: GrafVertex = v
    gv
  }

  implicit def toAttachableGrafEdge(at: Attachable[Edge]): Attachable[GrafEdge] = at map { e ⇒
    val ge: GrafEdge = e
    ge
  }
}
