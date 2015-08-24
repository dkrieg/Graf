package graf.gremlin
package process.traversal

import graf.gremlin.process.traversal.traverser._

import scala.collection.JavaConversions._
import graf.gremlin.process.traversal.dsl.graph._

case class GrafStep[Start, End](private[traversal] val step: Step[Start, End]) extends Iterator[GrafTraverser[End]] {

  def remove(): Unit = step.remove()

  def forEachRemaining(action: GrafTraverser[End] ⇒ Unit): Unit = step.forEachRemaining(action)

  def setTraversal(traversal: GrafTraversalAdmin[_, _]): Unit = step.setTraversal(traversal)

  def reset(): Unit = step.reset()

  def getTraversal[A, B]: GrafTraversalAdmin[A, B] = step.getTraversal.asInstanceOf[TraversalAdmin[A, B]]

  def equals(other: GrafStep[_, _], compareIds: Boolean): Boolean = step.equals(other, compareIds)

  def addLabel(label: String): Unit = step.addLabel(label)

  def getPreviousStep: GrafStep[_, Start] = step.getPreviousStep

  def getId: String = step.getId

  def getLabels: Set[String] = step.getLabels.toSet

  def addStart(start: GrafTraverser[Start]): Unit = step.addStart(start)

  def getNextStep: GrafStep[End, _] = step.getNextStep

  def removeLabel(label: String): Unit = step.removeLabel(label)

  def setPreviousStep(s: GrafStep[_, Start]): Unit = step.setPreviousStep(s)

  def setNextStep(s: GrafStep[End, _]): Unit = step.setNextStep(s)

  def getRequirements: Set[TraverserRequirement] = step.getRequirements.toSet map toTraverserRequirement

  def setId(id: String): Unit = step.setId(id)

  def addStarts(starts: Iterator[GrafTraverser[Start]]): Unit = step.addStarts(starts)

  override def hasNext: Boolean = step.hasNext

  override def next(): GrafTraverser[End] = step.next()
}
