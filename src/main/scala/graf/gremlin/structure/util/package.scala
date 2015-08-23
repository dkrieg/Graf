package graf.gremlin.structure

import org.apache.tinkerpop.gremlin.structure.util.{ Attachable ⇒ JAttachable }

package object util {
  type Attachable[A] = JAttachable[A]

  implicit class MappableAttachable[A](at: Attachable[A]) {
    def map[B](f: A ⇒ B): Attachable[B] = new Attachable[B] {
      override def get(): B = f(at.get)
    }
  }
}
