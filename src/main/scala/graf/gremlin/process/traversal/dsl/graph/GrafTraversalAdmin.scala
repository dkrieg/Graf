package graf.gremlin
package process.traversal.dsl.graph

import org.apache.tinkerpop.gremlin.process.traversal.Traversal.Admin
import org.apache.tinkerpop.gremlin.process.traversal._
import org.apache.tinkerpop.gremlin.process.traversal.step.TraversalParent
import org.apache.tinkerpop.gremlin.process.traversal.traverser.TraverserRequirement
import org.apache.tinkerpop.gremlin.structure.Graph
import structure.convert.wrapAll._

class GrafTraversalAdmin[Start, End](private[graph] val admin: Traversal.Admin[Start, End]) extends GrafTraversal[Start, End](admin.traversal) {

  def equals(other: GrafTraversalAdmin[Start, End]): Boolean = admin.equals(other.admin)

  def addStart(start: Traverser[Start]): Unit = admin.addStart(start)

  def addStep[E2](step: Step[_, E2]): Admin[Start, E2] = admin.addStep(step)

  def getSideEffects: TraversalSideEffects = admin.getSideEffects

  def getStrategies: TraversalStrategies = admin.getStrategies

  def getSteps: List[Step[_, _]] = admin.getSteps.toList

  def setEngine(engine: TraversalEngine): Unit = admin.setEngine(engine)

  def getTraverserGenerator: TraverserGenerator = admin.getTraverserGenerator

  def isLocked: Boolean = admin.isLocked

  def removeStep[S2, E2](index: Int): Traversal.Admin[S2, E2] = admin.removeStep(index)

  def getEngine: TraversalEngine = admin.getEngine

  def getEndStep: Step[_, End] = admin.getEndStep

  def reset(): Unit = admin.reset()

  def getGraph: Option[Graph] = admin.getGraph

  def getTraverserRequirements: Set[TraverserRequirement] = admin.getTraverserRequirements.toSet

  def addStep[S2, E2](index: Int, step: Step[_, _]): Traversal.Admin[S2, E2] = admin.addStep(index, step)

  def applyStrategies(): Unit = admin.applyStrategies()

  def setGraph(graph: Graph): Unit = admin.setGraph(graph)

  def setParent(step: TraversalParent): Unit = admin.setParent(step)

  def setSideEffects(sideEffects: TraversalSideEffects): Unit = admin.setSideEffects(sideEffects)

  def getStartStep: Step[Start, _] = admin.getStartStep

  def removeStep[S2, E2](step: Step[_, _]): Traversal.Admin[S2, E2] = admin.removeStep(step)

  def setStrategies(strategies: TraversalStrategies): Unit = admin.setStrategies(strategies)

  def getParent: TraversalParent = admin.getParent

  def addStarts(starts: Iterator[Traverser[Start]]): Unit = admin.addStarts(starts)
}
