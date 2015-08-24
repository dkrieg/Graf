package graf.gremlin
package process.traversal

import java.util.Optional
import java.util.function.UnaryOperator

import graf.gremlin.structure._

import scala.collection.JavaConversions._

case class GrafTraversalSideEffects(private[traversal] val traversalSideEffects: TraversalSideEffects) {

  def forEach[V](biConsumer: (String, V) ⇒ Unit): Unit =
    traversalSideEffects.forEach(biConsumer)

  def keys: Set[String] = traversalSideEffects.keys().toSet

  def mergeInto(sideEffects: GrafTraversalSideEffects): Unit =
    traversalSideEffects.mergeInto(sideEffects.traversalSideEffects)

  def get[V](key: String): Option[V] =
    traversalSideEffects.get(key).asScala

  def setLocalVertex(vertex: GrafVertex): Unit =
    traversalSideEffects.setLocalVertex(vertex)

  def getSackSplitOperator[S]: Option[S ⇒ S] =
    traversalSideEffects.getSackSplitOperator.asInstanceOf[Optional[UnaryOperator[S]]].asScala map unaryOpAsFn

  def remove(key: String): Unit =
    traversalSideEffects.remove(key)

  def getRegisteredSupplier[V](key: String): Option[Unit ⇒ V] =
    traversalSideEffects.getRegisteredSupplier(key).asScala map supplierToFn

  def registerSupplierIfAbsent(key: String, supplier: ⇒ Any): Unit =
    traversalSideEffects.registerSupplierIfAbsent(key, supplier)

  def getOrCreate[V](key: String, orCreate: ⇒ V): V = traversalSideEffects.getOrCreate(key, orCreate)

  def setSack[S](initialValue: ⇒ S, splitOperator: Option[S ⇒ S]): Unit =
    traversalSideEffects.setSack(fnToSupplier(initialValue), splitOperator.map(fnToUnaryOp))

  def set(key: String, value: Any): Unit =
    traversalSideEffects.set(key, value)

  def registerSupplier(key: String, supplier: ⇒ Any): Unit =
    traversalSideEffects.registerSupplier(key, supplier)

  def getSackInitialValue[S]: Option[Unit ⇒ S] =
    traversalSideEffects.getSackInitialValue.asScala map supplierToFn
}
