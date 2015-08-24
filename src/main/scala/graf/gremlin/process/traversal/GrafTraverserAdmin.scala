package graf.gremlin
package process.traversal

import graf.gremlin.structure.util._

class GrafTraverserAdmin[T](private[traversal] val admin: TraverserAdmin[T]) extends GrafTraverser[T](admin.traverser) {

  def detach(): GrafTraverserAdmin[T] = new GrafTraverserAdmin(admin.detach())

  def incrLoops(stepLabel: String): Unit = admin.incrLoops(stepLabel)

  def setSideEffects(sideEffects: GrafTraversalSideEffects): Unit = admin.setSideEffects(sideEffects)

  def resetLoops(): Unit = admin.resetLoops()

  def split[R](r: R, step: GrafStep[T, R]): GrafTraverserAdmin[R] = new GrafTraverserAdmin(admin.split(r, step))

  def setBulk(count: Long): Unit = admin.setBulk(count)

  def setStepId(stepId: String): Unit = admin.setStepId(stepId)

  def isHalted: Boolean = admin.isHalted

  def attach(method: Attachable[T] => T): T = admin.attach(method)

  def split(): GrafTraverserAdmin[T] = new GrafTraverserAdmin(admin.split())

  def getSideEffects: GrafTraversalSideEffects = admin.getSideEffects

  def getStepId: String = admin.getStepId

  def set(t: T): Unit = admin.set(t)

  def merge(other: GrafTraverserAdmin[_]): Unit = admin.merge(other.admin)

  override def get(): T = admin.get()
}
