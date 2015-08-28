package graf.gremlin
package process.traversal.dsl.graph

import org.apache.tinkerpop.gremlin.process.traversal.step.util.Tree
import org.apache.tinkerpop.gremlin.process.traversal._
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{ __, GraphTraversal }
import org.apache.tinkerpop.gremlin.structure._
import structure.convert.wrapAll._
import structure.convert.decorateAll._
import java.lang.{ Long ⇒ JLong, Double ⇒ JDouble }
import java.util.{ Map ⇒ JMap, List ⇒ JList }

class GrafGraphTraversal[S, E](private[graph] override val traversal: GraphTraversal[S, E]) extends GrafTraversal[S, E](traversal) {
  private def startV: GrafVertexTraversal[Vertex] = __.start[Vertex]()

  private def startE: GrafEdgeTraversal[Edge] = __.start[Edge]()

  ///////////////////// MAP STEPS /////////////////////

  def mapT[E2](function: Traverser[E] ⇒ E2): GrafGraphTraversal[S, E2] =
    traversal.map(function)

  def mapV[E2](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.map(f(startV))

  def mapE[E2](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.map(f(startE))

  def flatMapT[E2](f: Traverser[E] ⇒ Iterator[E2]): GrafGraphTraversal[S, E2] =
    traversal.flatMap[E2]((t: Traverser[E]) ⇒ f(t).asJava)

  def flatMapV[E2](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.flatMap(f(startV))

  def flatMapE[E2](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.flatMap(f(startE))

  def identity(): GrafGraphTraversal[S, E] =
    traversal.identity()

  def constant[E2](e: E2): GrafGraphTraversal[S, E2] =
    traversal.constant(e)

  def order: GrafGraphTraversal[S, E] =
    traversal.order()

  def order(scope: Scope): GrafGraphTraversal[S, E] =
    traversal.order(scope)

  def path: GrafGraphTraversal[S, Path] =
    traversal.path()

  def matchV[E2](f: Seq[(GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.`match`(f.map(t ⇒ toTraversal(t(startV))): _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def matchE[E2](f: Seq[(GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.`match`(f.map(t ⇒ toTraversal(t(startE))): _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def sack[E2](): GrafGraphTraversal[S, E2] =
    traversal.sack().asInstanceOf[GraphTraversal[S, E2]]

  def select[E2](pop: Pop, selectKey1: String, selectKey2: String, otherSelectKeys: String*): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.select(pop, selectKey1, selectKey2, otherSelectKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def select[E2](selectKey1: String, selectKey2: String, otherSelectKeys: String*): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.select(selectKey1, selectKey2, otherSelectKeys: _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def select[E2](pop: Pop, selectKey: String): GrafGraphTraversal[S, E2] =
    traversal.select(pop, selectKey).asInstanceOf[GraphTraversal[S, E2]]

  def select[E2](selectKey: String): GrafGraphTraversal[S, E2] =
    traversal.select(selectKey).asInstanceOf[GraphTraversal[S, E2]]

  def unfold[E2]: GrafGraphTraversal[S, E2] =
    traversal.unfold().asInstanceOf[GraphTraversal[S, E2]]

  def fold: GrafGraphTraversal[S, JList[E]] =
    traversal.fold()

  def fold[E2](seed: E2, fold: (E2, E) ⇒ E2): GrafGraphTraversal[S, E2] =
    traversal.fold(seed, fold)

  def count: GrafGraphTraversal[S, JLong] =
    traversal.count()

  def count(scope: Scope): GrafGraphTraversal[S, JLong] =
    traversal.count(scope)

  def sum: GrafGraphTraversal[S, JDouble] =
    traversal.sum()

  def sum(scope: Scope): GrafGraphTraversal[S, JDouble] =
    traversal.sum(scope)

  def max[E2 <: Number]: GrafGraphTraversal[S, E2] =
    traversal.max().asInstanceOf[GraphTraversal[S, E2]]

  def max[E2 <: Number](scope: Scope): GrafGraphTraversal[S, E2] =
    traversal.max(scope).asInstanceOf[GraphTraversal[S, E2]]

  def min[E2 <: Number]: GrafGraphTraversal[S, E2] =
    traversal.min().asInstanceOf[GraphTraversal[S, E2]]

  def min[E2 <: Number](scope: Scope): GrafGraphTraversal[S, E2] =
    traversal.min(scope).asInstanceOf[GraphTraversal[S, E2]]

  def mean: GrafGraphTraversal[S, JDouble] =
    traversal.mean()

  def mean(scope: Scope): GrafGraphTraversal[S, JDouble] =
    traversal.mean(scope)

  def group[K, R]: GrafGraphTraversal[S, JMap[K, R]] =
    traversal.group().asInstanceOf[GraphTraversal[S, JMap[K, R]]]

  def groupCount[E2]: GrafGraphTraversal[S, JMap[E2, JLong]] =
    traversal.groupCount().asInstanceOf[GraphTraversal[S, JMap[E2, JLong]]]

  def tree: GrafGraphTraversal[S, Tree[_]] =
    traversal.tree()

  def addV(kv: AnyRef, keyValues: AnyRef*): GrafVertexTraversal[S] =
    traversal.addV(kv +: keyValues: _*)

  def addE(
    direction: Direction,
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: AnyRef*): GrafEdgeTraversal[S] =
    traversal.addE(
      direction,
      firstVertexKeyOrEdgeLabel,
      edgeLabelOrSecondVertexKey,
      propertyKeyValues)

  def addOutE(
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: AnyRef*): GrafEdgeTraversal[S] =
    traversal.addOutE(
      firstVertexKeyOrEdgeLabel,
      edgeLabelOrSecondVertexKey,
      propertyKeyValues: _*)

  def addInE(
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: AnyRef*): GrafEdgeTraversal[S] =
    traversal.addInE(
      firstVertexKeyOrEdgeLabel,
      edgeLabelOrSecondVertexKey,
      propertyKeyValues: _*)

  ///////////////////// FILTER STEPS /////////////////////

  def filterT(p: Traverser[E] ⇒ Boolean): GrafGraphTraversal[S, E] =
    traversal.filter(p)

  def filterV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.filter(f(startV))

  def filterE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.filter(f(startE))

  def orV(fs: Seq[(GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, E] =
    traversal.or(fs.map(t ⇒ toTraversal(t(startV))): _*)

  def orE(fs: Seq[(GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, E] =
    traversal.or(fs.map(t ⇒ toTraversal(t(startE))): _*)

  def andV(fs: Seq[(GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, E] =
    traversal.and(fs.map(t ⇒ toTraversal(t(startV))): _*)

  def andE(fs: Seq[(GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, E] =
    traversal.and(fs.map(t ⇒ toTraversal(t(startE))): _*)

  def inject(i: E, injections: E*): GrafGraphTraversal[S, E] =
    traversal.inject(i +: injections: _*)

  def dedup: GrafGraphTraversal[S, E] = traversal.dedup()

  def dedup(dl: String, dedupLabels: String*): GrafGraphTraversal[S, E] =
    traversal.dedup(dl +: dedupLabels: _*)

  def dedup(scope: Scope, dedupLabels: String*): GrafGraphTraversal[S, E] =
    traversal.dedup(scope, dedupLabels: _*)

  def whereV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.where(f(startV))

  def whereE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.where(f(startE))

  def where(startKey: String, predicate: P[String]): GrafGraphTraversal[S, E] =
    traversal.where(startKey, predicate)

  def where(predicate: P[String]): GrafGraphTraversal[S, E] =
    traversal.where(predicate)

  def hasV(propertyKey: String, f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey, f(startV))

  def hasE(propertyKey: String, f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey, f(startE))

  def is(predicate: P[E]): GrafGraphTraversal[S, E] =
    traversal.is(predicate)

  def is(value: Any): GrafGraphTraversal[S, E] =
    traversal.is(value)

  def notV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.not(f(startV))

  def notE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.not(f(startE))

  def coin(probability: Double): GrafGraphTraversal[S, E] =
    traversal.coin(probability)

  def range(low: Long, high: Long): GrafGraphTraversal[S, E] =
    traversal.range(low, high)

  def range[E2](scope: Scope, low: Long, high: Long): GrafGraphTraversal[S, E2] =
    traversal.range(scope, low, high).asInstanceOf[GraphTraversal[S, E2]]

  def limit(limit: Long): GrafGraphTraversal[S, E] =
    traversal.limit(limit)

  def limit[E2](scope: Scope, limit: Long): GrafGraphTraversal[S, E2] =
    traversal.limit(scope, limit).asInstanceOf[GraphTraversal[S, E2]]

  def tail(limit: Long): GrafGraphTraversal[S, E] =
    traversal.tail(limit)

  def tail[E2](scope: Scope): GrafGraphTraversal[S, E2] =
    traversal.tail(scope).asInstanceOf[GraphTraversal[S, E2]]

  def tail: GrafGraphTraversal[S, E] =
    traversal.tail()

  def tail[E2](scope: Scope, limit: Long): GrafGraphTraversal[S, E2] =
    traversal.tail(scope, limit).asInstanceOf[GraphTraversal[S, E2]]

  def timeLimit(timeLimit: Long): GrafGraphTraversal[S, E] =
    traversal.timeLimit(timeLimit)

  def simplePath: GrafGraphTraversal[S, E] =
    traversal.simplePath()

  def cyclicPath: GrafGraphTraversal[S, E] =
    traversal.cyclicPath()

  def sample(scope: Scope, amountToSample: Int): GrafGraphTraversal[S, E] =
    traversal.sample(scope, amountToSample)

  def sample(amountToSample: Int): GrafGraphTraversal[S, E] =
    traversal.sample(amountToSample)

  def drop: GrafGraphTraversal[S, E] =
    traversal.drop()

  ///////////////////// SIDE-EFFECT STEPS /////////////////////

  def sideEffectT(consumer: Traverser[E] ⇒ Unit): GrafGraphTraversal[S, E] =
    traversal.sideEffect(consumer)

  def sideEffectV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.sideEffect(f(startV))

  def sideEffectE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.sideEffect(f(startE))

  def cap[E2](sideEffectKey: String, sideEffectKeys: String*): GrafGraphTraversal[S, E2] =
    traversal.cap(sideEffectKey, sideEffectKeys: _*).asInstanceOf[GraphTraversal[S, E2]]

  def subgraph(sideEffectKey: String): GrafEdgeTraversal[S] =
    traversal.subgraph(sideEffectKey)

  def aggregate(sideEffectKey: String): GrafGraphTraversal[S, E] =
    traversal.aggregate(sideEffectKey)

  def group(sideEffectKey: String): GrafGraphTraversal[S, E] =
    traversal.group(sideEffectKey)

  def groupCount(sideEffectKey: String): GrafGraphTraversal[S, E] =
    traversal.groupCount(sideEffectKey)

  def tree(sideEffectKey: String): GrafGraphTraversal[S, E] =
    traversal.tree(sideEffectKey)

  def sack[V](sackFunction: (V, E) ⇒ V): GrafGraphTraversal[S, E] =
    traversal.sack(sackFunction)

  def sack[V](sackOperator: (V, V) ⇒ V, elementPropertyKey: String): GrafGraphTraversal[S, E] =
    traversal.sack(sackOperator, elementPropertyKey)

  def store(sideEffectKey: String): GrafGraphTraversal[S, E] =
    traversal.store(sideEffectKey)

  def profile: GrafGraphTraversal[S, E] =
    traversal.profile()

  ///////////////////// BRANCH STEPS /////////////////////

  def branchT[M, E2](f: Traverser[E] ⇒ M): GrafGraphTraversal[S, E2] =
    traversal.branch((t: Traverser[E]) ⇒ f(t)).asInstanceOf[GraphTraversal[S, E2]]

  def branchV[M, E2](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, M]): GrafGraphTraversal[S, E2] =
    traversal.branch(f(startV)).asInstanceOf[GraphTraversal[S, E2]]

  def branchE[M, E2](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, M]): GrafGraphTraversal[S, E2] =
    traversal.branch(f(startE)).asInstanceOf[GraphTraversal[S, E2]]

  def chooseV[M, E2](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, M]): GrafGraphTraversal[S, E2] =
    traversal.choose(f(startV)).asInstanceOf[GraphTraversal[S, E2]]

  def chooseE[M, E2](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, M]): GrafGraphTraversal[S, E2] =
    traversal.choose(f(startE)).asInstanceOf[GraphTraversal[S, E2]]

  def chooseV[E2](
    p: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _],
    t: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2],
    f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.choose(
      p(startV), t(startV), f(startV))

  def chooseE[E2](
    p: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _],
    t: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2],
    f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.choose(
      p(startE), t(startE), f(startE))

  def choosePV[E2](
    p: E ⇒ Boolean,
    t: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2],
    f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.choose(p, t(startV), f(startV))

  def choosePE[E2](
    p: E ⇒ Boolean,
    t: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2],
    f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.choose(p, t(startE), f(startE))

  def choose[M, E2](choiceFunction: E ⇒ M): GrafGraphTraversal[S, E2] =
    traversal.choose(choiceFunction).asInstanceOf[GraphTraversal[S, E2]]

  def unionV[E2](
    fs: Seq[(GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2])]): GrafGraphTraversal[S, E2] =
    traversal.union(fs.map(t ⇒ toTraversal(t(startV))): _*)

  def unionE[E2](
    fs: Seq[(GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2])]): GrafGraphTraversal[S, E2] =
    traversal.union(fs.map(t ⇒ toTraversal(t(startE))): _*)

  def coalesceV[E2](
    fs: Seq[(GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2])]): GrafGraphTraversal[S, E2] =
    traversal.coalesce(fs.map(t ⇒ toTraversal(t(startV))): _*)

  def coalesceE[E2](
    fs: Seq[(GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2])]): GrafGraphTraversal[S, E2] =
    traversal.coalesce(fs.map(t ⇒ toTraversal(t(startE))): _*)

  def repeatV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E]): GrafGraphTraversal[S, E] =
    traversal.repeat(f(startV))

  def repeatE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E]): GrafGraphTraversal[S, E] =
    traversal.repeat(f(startE))

  def emit: GrafGraphTraversal[S, E] = traversal.emit()

  def emitT(emitPredicate: Traverser[E] ⇒ Boolean): GrafGraphTraversal[S, E] =
    traversal.emit(emitPredicate)

  def emitV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.emit(f(startV))

  def emitE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.emit(f(startE))

  def untilT(until: Traverser[E] ⇒ Boolean): GrafGraphTraversal[S, E] =
    traversal.until((u: Traverser[E]) ⇒ until(u))

  def untilV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.until(f(startV))

  def untilE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.until(f(startE))

  def times(maxLoops: Int): GrafGraphTraversal[S, E] =
    traversal.times(maxLoops)

  def localV[E2](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.local(f(startV))

  def localE[E2](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.local(f(startE))

  ///////////////////// UTILITY STEPS /////////////////////

  def as(stepLabel: String, stepLabels: String*): GrafGraphTraversal[S, E] =
    traversal.as(stepLabel, stepLabels: _*)

  def barrier: GrafGraphTraversal[S, E] =
    traversal.barrier()

  def barrier(maxBarrierSize: Int): GrafGraphTraversal[S, E] =
    traversal.barrier(maxBarrierSize)

  def by: GrafGraphTraversal[S, E] =
    traversal.by()

  def by[V](element: Element ⇒ V, comparator: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(element, comparator)

  def by(order: Order): GrafGraphTraversal[S, E] =
    traversal.by(order)

  def by(tokenProjection: T): GrafGraphTraversal[S, E] =
    traversal.by(tokenProjection)

  def byV(f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.by(f(startV))

  def byE(f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.by(f(startE))

  def byV[V](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[_, _], endOrdering: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(f(startV), endOrdering)

  def byE[V](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[_, _], endOrdering: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(f(startE), endOrdering)

  def by(elementPropertyKey: String): GrafGraphTraversal[S, E] =
    traversal.by(elementPropertyKey)

  def by(comparator: Ordering[E]): GrafGraphTraversal[S, E] =
    traversal.by(comparator)

  def by[V](f: V ⇒ AnyRef): GrafGraphTraversal[S, E] =
    traversal.by(f)

  def by[V](elementPropertyProjection: String, propertyValueComparator: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(elementPropertyProjection, propertyValueComparator)

  def optionV[M, E2](
    pickToken: M,
    f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[E, E2]): GrafGraphTraversal[S, E] =
    traversal.option(pickToken, f(startV))

  def optionE[M, E2](
    pickToken: M,
    f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[E, E2]): GrafGraphTraversal[S, E] =
    traversal.option(pickToken, f(startE))

  def optionV[E2](f: GrafVertexTraversal[Vertex] ⇒ GrafTraversal[E, E2]): GrafGraphTraversal[S, E] =
    traversal.option(f(startV))

  def optionE[E2](f: GrafEdgeTraversal[Edge] ⇒ GrafTraversal[E, E2]): GrafGraphTraversal[S, E] =
    traversal.option(f(startE))

  def iterate(): GrafGraphTraversal[S, E] =
    traversal.iterate()

}
