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
    def key = keyT[Object]

    def keyT[T <: AnyRef]: T

    def value = valueT[Object]

    def valueT[T <: Any]: T
  }

  sealed trait Label extends Atom

  object Label {
    def apply(s: String): Label = new AtomValue(label, s) with Label {
      override def toString: String = s"(T.label, $s)"
    }
  }

  sealed trait ID extends Atom

  object ID {
    def apply(s: Any): ID = new AtomValue(id, s) with ID {
      override def toString: String = s"(T.id, $s)"
    }
  }

  trait KeyValue extends Atom

  case class Key[B](key: String) {
    def apply(value: B): KeyValue = new AtomValue[String, B](key, value) with KeyValue {
      override def toString: String = s"($key, $value)"
    }
  }

  private[schema] class AtomValue[A <: AnyRef, B <: Any] private[schema] (k: A, v: B) {
    def keyT[T <: AnyRef] = k.asInstanceOf[T]

    def valueT[T <: Any] = v.asInstanceOf[T]
  }

  implicit def labelToString(label: Label): String = label.valueT[String]

  implicit def keyToString[B](k: Key[B]): String = k.key
}

