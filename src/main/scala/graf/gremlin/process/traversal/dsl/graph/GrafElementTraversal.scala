package graf.gremlin
package process.traversal.dsl.graph

import java.util.{ Map ⇒ JMap }

import graf.gremlin.structure.schema.{ Label, KeyValue, ID }
import org.apache.tinkerpop.gremlin.process.traversal.P
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal
import org.apache.tinkerpop.gremlin.structure.VertexProperty.Cardinality
import org.apache.tinkerpop.gremlin.structure.{ Vertex, Property, T }

class GrafElementTraversal[S, E](private[graph] override val traversal: GraphTraversal[S, E]) extends GrafGraphTraversal[S, E](traversal) {

  def id: GrafGraphTraversal[S, AnyRef] =
    traversal.id()

  def key: GrafElementTraversal[S, String] = traversal.key()

  def label: GrafElementTraversal[S, String] = traversal.label()

  def mapKeys[E2]: GrafElementTraversal[S, E2] =
    traversal.mapKeys().asInstanceOf[GraphTraversal[S, E2]]

  def mapValues[E2]: GrafElementTraversal[S, E2] =
    traversal.mapValues().asInstanceOf[GraphTraversal[S, E2]]

  def properties[E2]: GrafElementTraversal[S, _ <: Property[E2]] =
    traversal.properties().asInstanceOf[GraphTraversal[S, _ <: Property[E2]]]

  def properties[E2](pk: String, propertyKeys: String*): GrafElementTraversal[S, _ <: Property[E2]] =
    traversal.properties(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, _ <: Property[E2]]]

  def property(cardinality: Cardinality, key: String, value: Any, keyValues: AnyRef*): GrafElementTraversal[S, E] =
    traversal.property(cardinality, key, value, keyValues: _*)

  def property(key: String, value: Any, keyValues: AnyRef*): GrafElementTraversal[S, E] =
    traversal.property(key, value, keyValues: _*)

  def propertyMap[E2]: GrafElementTraversal[S, JMap[String, E2]] =
    traversal.propertyMap().asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def propertyMap[E2](pk: String, propertyKeys: String*): GrafElementTraversal[S, JMap[String, E2]] =
    traversal.propertyMap(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def value[E2]: GrafElementTraversal[S, E2] =
    traversal.value().asInstanceOf[GraphTraversal[S, E2]]

  def valueMap[E2](includeTokens: Boolean, propertyKeys: String*): GrafElementTraversal[S, JMap[String, E2]] =
    traversal.valueMap(includeTokens, propertyKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def valueMap[E2]: GrafElementTraversal[S, JMap[String, E2]] =
    traversal.valueMap().asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def valueMap[E2](pk: String, propertyKeys: String*): GrafElementTraversal[S, JMap[String, E2]] =
    traversal.valueMap(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def values[E2]: GrafElementTraversal[S, E2] =
    traversal.values().asInstanceOf[GraphTraversal[S, E2]]

  def values[E2](pk: String, propertyKeys: String*): GrafElementTraversal[S, E2] =
    traversal.values(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, E2]]

  def has(accessor: T, predicate: P[_]): GrafElementTraversal[S, E] =
    traversal.has(accessor, predicate)

  def has(accessor: T, value: Any): GrafElementTraversal[S, E] =
    traversal.has(accessor, value)

  def has(label: String, propertyKey: String, predicate: P[_]): GrafElementTraversal[S, E] =
    traversal.has(label, propertyKey, predicate)

  def has(label: String, propertyKey: String, value: Any): GrafElementTraversal[S, E] =
    traversal.has(label, propertyKey, value)

  def has(propertyKey: String): GrafElementTraversal[S, E] =
    traversal.has(propertyKey)

  def has(propertyKey: String, predicate: P[_]): GrafElementTraversal[S, E] =
    traversal.has(propertyKey, predicate)

  def has(propertyKey: String, value: Any): GrafElementTraversal[S, E] =
    traversal.has(propertyKey, value)

  def hasId(id: AnyRef, ids: AnyRef*): GrafElementTraversal[S, E] =
    traversal.hasId(id +: ids: _*)

  def hasKey(key: String, keys: String*): GrafElementTraversal[S, E] =
    traversal.hasKey(key +: keys: _*)

  def hasLabel(label: String, labels: String*): GrafElementTraversal[S, E] =
    traversal.hasLabel(label +: labels: _*)

  def hasNot(propertyKey: String): GrafElementTraversal[S, E] =
    traversal.hasNot(propertyKey)

  def hasValue(value: AnyRef, values: AnyRef*): GrafElementTraversal[S, E] =
    traversal.hasValue(value +: values: _*)

  def hasId(id: ID): GrafElementTraversal[S, E] =
    traversal.hasId(id.value)

  def hasKeyValue(keyValue: KeyValue): GrafElementTraversal[S, E] =
    traversal.has(keyValue.key.asInstanceOf[String], keyValue.value)

  def hasLabel(label: Label): GrafElementTraversal[S, E] =
    traversal.hasLabel(label.value.asInstanceOf[String])
}
