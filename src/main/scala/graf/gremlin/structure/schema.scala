package graf.gremlin
package structure

import org.apache.tinkerpop.gremlin.structure.T

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
    def apply(label: String): Label = new AtomValue(T.label, label) with Label
  }

  sealed trait ID extends Atom
  object ID {
    def apply(id: Any): ID = new AtomValue(T.id, id) with ID
  }

  case class Key[B](key: String) {
    def apply(value: B): Atom = new AtomValue[String, B](key -> value) with Atom
  }

  private[schema] class AtomValue[A <: AnyRef, B <: Any] private[schema] (p: (A, B)) {
    def key = p._1.asInstanceOf[Object]
    def value = p._2.asInstanceOf[Object]
  }
}



