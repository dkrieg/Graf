package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure.schema._
import org.apache.tinkerpop.gremlin.process.traversal.P
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.structure.{ T, Property }
import org.apache.tinkerpop.gremlin.structure.VertexProperty.Cardinality
import java.util.{ Map ⇒ JMap }

class GrafElementTraversal[S, E](private[graph] override val traversal: GraphTraversal[S, E]) extends GrafGraphTraversal[S, E](traversal) {

  def id: GrafGraphTraversal[S, AnyRef] =
    traversal.id()

  def key: GrafGraphTraversal[S, String] = traversal.key()

  def label: GrafGraphTraversal[S, String] = traversal.label()

  def local[E2](localTraversal: GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.local(localTraversal)

  def mapKeys[E2]: GrafGraphTraversal[S, E2] =
    traversal.mapKeys().asInstanceOf[GraphTraversal[S, E2]]

  def mapValues[E2]: GrafGraphTraversal[S, E2] =
    traversal.mapValues().asInstanceOf[GraphTraversal[S, E2]]

  def properties[E2]: GrafGraphTraversal[S, _ <: Property[E2]] =
    traversal.properties().asInstanceOf[GraphTraversal[S, _ <: Property[E2]]]

  def properties[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[S, _ <: Property[E2]] =
    traversal.properties(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, _ <: Property[E2]]]

  def property(cardinality: Cardinality, key: String, value: Any, keyValues: AnyRef*): GrafGraphTraversal[S, E] =
    traversal.property(cardinality, key, value, keyValues)

  def property(key: String, value: Any, keyValues: AnyRef*): GrafGraphTraversal[S, E] =
    traversal.property(key, value, keyValues)

  def propertyMap[E2]: GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.propertyMap().asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def propertyMap[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.propertyMap(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def value[E2]: GrafGraphTraversal[S, E2] =
    traversal.value().asInstanceOf[GraphTraversal[S, E2]]

  def valueMap[E2](includeTokens: Boolean, propertyKeys: String*): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.valueMap(includeTokens, propertyKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def valueMap[E2]: GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.valueMap().asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def valueMap[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.valueMap(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def values[E2]: GrafGraphTraversal[S, E2] =
    traversal.values().asInstanceOf[GraphTraversal[S, E2]]

  def values[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[S, E2] =
    traversal.values(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, E2]]

  def hasKeyValue(atom: KeyValue) = has(atom.key.asInstanceOf[String], P.eq(atom.value))

  def hasId(atom: ID) = has(atom.key.asInstanceOf[T], atom.value)

  def hasLabel(atom: Label) = has(atom.key.asInstanceOf[T], atom.value)

  def has(accessor: T, predicate: P[_]): GrafGraphTraversal[S, E] =
    traversal.has(accessor, predicate)

  def has(accessor: T, value: Any): GrafGraphTraversal[S, E] =
    traversal.has(accessor, value)

  def has(label: String, propertyKey: String, predicate: P[_]): GrafGraphTraversal[S, E] =
    traversal.has(label, propertyKey, predicate)

  def has(label: String, propertyKey: String, value: Any): GrafGraphTraversal[S, E] =
    traversal.has(label, propertyKey, value)

  def has(propertyKey: String): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey)

  def has(propertyKey: String, predicate: P[_]): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey, predicate)

  def has(propertyKey: String, propertyTraversal: GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey, propertyTraversal)

  def has(propertyKey: String, value: Any): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey, value)

  def hasId(id: AnyRef, ids: AnyRef*): GrafGraphTraversal[S, E] =
    traversal.hasId(id +: ids: _*)

  def hasKey(key: String, keys: String*): GrafGraphTraversal[S, E] =
    traversal.hasKey(key +: keys: _*)

  def hasLabel(label: String, labels: String*): GrafGraphTraversal[S, E] =
    traversal.hasLabel(label +: labels: _*)

  def hasNot(propertyKey: String): GrafGraphTraversal[S, E] =
    traversal.hasNot(propertyKey)

  def hasValue(value: AnyRef, values: AnyRef*): GrafGraphTraversal[S, E] =
    traversal.hasValue(value +: values: _*)
}
