package graf.gremlin
package structure

import org.apache.tinkerpop.gremlin.structure.T._

import scala.language.implicitConversions

object schema {

  /**
   * Smaller than an Element, consisting of the particles used
   * compose a Property or VertexProperty of an Element (Edge or Vertex)
   */
  sealed trait Atom {
    def key: Object

    def value: Object
  }

  sealed trait Label extends Atom

  object Label {
    def apply(s: String): Label = new AtomValue(label, s) with Label
  }

  sealed trait ID extends Atom

  object ID {
    def apply(s: Any): ID = new AtomValue(id, s) with ID
  }

  trait KeyValue extends Atom

  case class Key[B](key: String) {
    def apply(value: B): KeyValue = new AtomValue[String, B](key, value) with KeyValue
  }

  private[schema] class AtomValue[A <: AnyRef, B <: Any] private[schema] (k: A, v: B) {
    def key = k.asInstanceOf[Object]

    def value = v.asInstanceOf[Object]
  }
}

