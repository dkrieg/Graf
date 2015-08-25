package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.structure.schema._
import org.apache.tinkerpop.gremlin.process.traversal.P


class GrafElementGraphTraversal[Start, End](private[graph] override val graphTraversal: GraphTraversal[Start, End]) extends GrafGraphTraversal[Start, End](graphTraversal) {

  def and(andTraversals: GrafTraversal[_, _]*): GrafGraphTraversal[Start, End] =
    graphTraversal.and(andTraversals.map(toTraversal(_)): _*)

  def id: GrafGraphTraversal[Start, AnyRef] =
    graphTraversal.id()

  def key: GrafGraphTraversal[Start, String] = graphTraversal.key()

  def label: GrafGraphTraversal[Start, String] = graphTraversal.label()

  def local[E2](localTraversal: GrafTraversal[_, E2]): GrafGraphTraversal[Start, E2] =
    graphTraversal.local(localTraversal)

  def mapKeys[E2]: GrafGraphTraversal[Start, E2] =
    graphTraversal.mapKeys().asInstanceOf[GraphTraversal[Start, E2]]

  def mapValues[E2]: GrafGraphTraversal[Start, E2] =
    graphTraversal.mapValues().asInstanceOf[GraphTraversal[Start, E2]]

  def or: GrafGraphTraversal[Start, End] =
    graphTraversal.or()

  def or(ot: GrafTraversal[_, _], orTraversals: GrafTraversal[_, _]*): GrafGraphTraversal[Start, End] =
    graphTraversal.or((ot +: orTraversals).map(toTraversal(_)): _*)

  def properties[E2]: GrafGraphTraversal[Start, _ <: Property[E2]] =
    graphTraversal.properties().asInstanceOf[GraphTraversal[Start, _ <: Property[E2]]]

  def properties[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[Start, _ <: Property[E2]] =
    graphTraversal.properties(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[Start, _ <: Property[E2]]]

  def property(cardinality: Cardinality, key: String, value: Any, keyValues: AnyRef*): GrafGraphTraversal[Start, End] =
    graphTraversal.property(cardinality, key, value, keyValues)

  def property(key: String, value: Any, keyValues: AnyRef*): GrafGraphTraversal[Start, End] =
    graphTraversal.property(key, value, keyValues)

  def propertyMap[E2]: GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.propertyMap().asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def propertyMap[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.propertyMap(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def timeLimit(timeLimit: Long): GrafGraphTraversal[Start, End] =
    graphTraversal.timeLimit(timeLimit)

  def value[E2]: GrafGraphTraversal[Start, E2] =
    graphTraversal.value().asInstanceOf[GraphTraversal[Start, E2]]

  def valueMap[E2](includeTokens: Boolean, propertyKeys: String*): GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.valueMap(includeTokens, propertyKeys: _*).asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def valueMap[E2]: GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.valueMap().asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def valueMap[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.valueMap(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def values[E2]: GrafGraphTraversal[Start, E2] =
    graphTraversal.values().asInstanceOf[GraphTraversal[Start, E2]]

  def values[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[Start, E2] =
    graphTraversal.values(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[Start, E2]]

  def hasKeyValue(atom: KeyValue) = has(atom.key.asInstanceOf[String], P.eq(atom.value))

  def hasId(atom: ID) = has(atom.key.asInstanceOf[T], atom.value)

  def hasLabel(atom: Label) = has(atom.key.asInstanceOf[T], atom.value)

  def has(accessor: T, predicate: P[_]): GrafGraphTraversal[Start, End] =
    graphTraversal.has(accessor, predicate)

  def has(accessor: T, value: Any): GrafGraphTraversal[Start, End] =
    graphTraversal.has(accessor, value)

  def has(label: String, propertyKey: String, predicate: P[_]): GrafGraphTraversal[Start, End] =
    graphTraversal.has(label, propertyKey, predicate)

  def has(label: String, propertyKey: String, value: Any): GrafGraphTraversal[Start, End] =
    graphTraversal.has(label, propertyKey, value)

  def has(propertyKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.has(propertyKey)

  def has(propertyKey: String, predicate: P[_]): GrafGraphTraversal[Start, End] =
    graphTraversal.has(propertyKey, predicate)

  def has(propertyKey: String, propertyTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.has(propertyKey, propertyTraversal)

  def has(propertyKey: String, value: Any): GrafGraphTraversal[Start, End] =
    graphTraversal.has(propertyKey, value)

  def hasId(id: AnyRef, ids: AnyRef*): GrafGraphTraversal[Start, End] =
    graphTraversal.hasId(id +: ids: _*)

  def hasKey(key: String, keys: String*): GrafGraphTraversal[Start, End] =
    graphTraversal.hasKey(key +: keys: _*)

  def hasLabel(label: String, labels: String*): GrafGraphTraversal[Start, End] =
    graphTraversal.hasLabel(label +: labels: _*)

  def hasNot(propertyKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.hasNot(propertyKey)

  def hasValue(value: AnyRef, values: AnyRef*): GrafGraphTraversal[Start, End] =
    graphTraversal.hasValue(value +: values: _*)
}
