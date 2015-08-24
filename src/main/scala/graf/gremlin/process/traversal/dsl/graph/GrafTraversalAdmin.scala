package graf.gremlin
package process.traversal.dsl.graph

import graf.gremlin.process.traversal._
import graf.gremlin.structure._

class GrafTraversalAdmin[Start, End](private[graph] val admin: Admin[Start, End]) extends GrafTraversal[Start, End](admin.traversal) {

  def equals(other: GrafTraversalAdmin[Start, End]): Boolean = admin.equals(other.admin)

  def addStart(start: GrafTraverser[Start]): Unit = admin.addStart(start)

  //    def addStep[E2](step: Step[_, E2]): GrafAdmin[Start, E2] = admin.addStep(step)

  def getSideEffects: GrafTraversalSideEffects = admin.getSideEffects

  //    def getStrategies: TraversalStrategies = admin.getStrategies

  //    def getSteps: util.List[Step[_, _]] = admin.getSteps

  //    def setEngine(engine: TraversalEngine): Unit = admin.setEngine(engine)

  //    def getTraverserGenerator: TraverserGenerator = admin.getTraverserGenerator

  def isLocked: Boolean = admin.isLocked

  //    def removeStep[S2, E2](index: Int): Traversal.Admin[S2, E2] = admin.removeStep(index)

  //    def getEngine: TraversalEngine = admin.getEngine

  //    def getEndStep: Step[_, End] = admin.getEndStep

  def reset(): Unit = admin.reset()

  def getGraph: Option[GrafGraph] = admin.getGraph.asScala map toGrafGraph

  //    def getTraverserRequirements: util.Set[TraverserRequirement] = admin.getTraverserRequirements

  //    def addStep[S2, E2](index: Int, step: Step[_, _]): Traversal.Admin[S2, E2] = admin.addStep(index, step)

  def applyStrategies(): Unit = admin.applyStrategies()

  def setGraph(graph: Graph): Unit = admin.setGraph(graph)

  //    def setParent(step: TraversalParent): Unit = admin.setParent(step)

  def setSideEffects(sideEffects: GrafTraversalSideEffects): Unit = admin.setSideEffects(sideEffects)

  //    def getStartStep: Step[Start, _] = admin.getStartStep

  //    def removeStep[S2, E2](step: Step[_, _]): Traversal.Admin[S2, E2] = admin.removeStep(step)

  //    def setStrategies(strategies: TraversalStrategies): Unit = admin.setStrategies(strategies)

  //    def getParent: TraversalParent = admin.getParent

  //    def addStarts(starts: util.Iterator[Traverser[Start]]): Unit = admin.addStarts(starts)
}
