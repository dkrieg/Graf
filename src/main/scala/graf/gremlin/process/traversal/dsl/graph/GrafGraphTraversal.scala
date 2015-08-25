package graf.gremlin
package process.traversal.dsl.graph

import structure.convert.wrapAll._

class GrafGraphTraversal[Start, End](private[graph] val graphTraversal: GraphTraversal[Start, End]) extends GrafTraversal[Start, End](graphTraversal) {

  def cap[E2](sideEffectKey: String, sideEffectKeys: String*): GrafGraphTraversal[Start, E2] =
    graphTraversal.cap(sideEffectKey, sideEffectKeys: _*).asInstanceOf[GraphTraversal[Start, E2]]

  def by: GrafGraphTraversal[Start, End] =
    graphTraversal.by()

  def sample(scope: Scope, amountToSample: Int): GrafGraphTraversal[Start, End] =
    graphTraversal.sample(scope, amountToSample)

  def min[E2 <: Number]: GrafGraphTraversal[Start, E2] =
    graphTraversal.min().asInstanceOf[GraphTraversal[Start, E2]]

  def is(predicate: P[End]): GrafGraphTraversal[Start, End] =
    graphTraversal.is(predicate)

  def cyclicPath: GrafGraphTraversal[Start, End] =
    graphTraversal.cyclicPath()

  def tree: GrafGraphTraversal[Start, Tree[_]] =
    graphTraversal.tree()

  def drop: GrafGraphTraversal[Start, End] =
    graphTraversal.drop()

  def is(value: Any): GrafGraphTraversal[Start, End] =
    graphTraversal.is(value)

  def tree(sideEffectKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.tree(sideEffectKey)

  def count(scope: Scope): GrafGraphTraversal[Start, JLong] =
    graphTraversal.count(scope)

  def times(maxLoops: Int): GrafGraphTraversal[Start, End] =
    graphTraversal.times(maxLoops)

  def repeat(repeatTraversal: GrafTraversal[_, End]): GrafGraphTraversal[Start, End] =
    graphTraversal.repeat(repeatTraversal)

  def sideEffect(sideEffectTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.sideEffect(sideEffectTraversal)

  def to(direction: Direction, edgeLabels: String*): GrafGraphTraversal[Start, Vertex] =
    graphTraversal.to(direction, edgeLabels: _*)

  def select[E2](selectKey1: String, selectKey2: String, otherSelectKeys: String*): GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.select(selectKey1, selectKey2, otherSelectKeys: _*).asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def inject(injections: End*): GrafGraphTraversal[Start, End] =
    graphTraversal.inject(injections: _*)

  def by[V](element: Element ⇒ V, comparator: Ordering[V]): GrafGraphTraversal[Start, End] =
    graphTraversal.by(element, comparator)

  def select[E2](pop: Pop, selectKey1: String, selectKey2: String, otherSelectKeys: String*): GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.select(pop, selectKey1, selectKey2, otherSelectKeys: _*).asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def group[K, R]: GrafGraphTraversal[Start, JMap[K, R]] =
    graphTraversal.group().asInstanceOf[GraphTraversal[Start, JMap[K, R]]]

  def map[E2](mapTraversal: GrafTraversal[_, E2]): GrafGraphTraversal[Start, E2] =
    graphTraversal.map(mapTraversal)

  def where(whereTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.where(whereTraversal)

  def choose[E2](traversalPredicate: GrafTraversal[_, _], trueChoice: GrafTraversal[_, E2], falseChoice: GrafTraversal[_, E2]): GrafGraphTraversal[Start, E2] =
    graphTraversal.choose(traversalPredicate, trueChoice, falseChoice)

  def tail(limit: Long): GrafGraphTraversal[Start, End] =
    graphTraversal.tail(limit)

  def emit(emitPredicate: Traverser[End] ⇒ Boolean): GrafGraphTraversal[Start, End] =
    graphTraversal.emit(emitPredicate)

  def sum(): GrafGraphTraversal[Start, JDouble] =
    graphTraversal.sum()

  def max[E2 <: Number](): GrafGraphTraversal[Start, E2] =
    graphTraversal.max().asInstanceOf[GraphTraversal[Start, E2]]

  def union[E2](unionTraversals: GrafTraversal[_, E2]*): GrafGraphTraversal[Start, E2] =
    graphTraversal.union(unionTraversals.map(toTraversal(_)): _*)

  def constant[E2](e: E2): GrafGraphTraversal[Start, E2] =
    graphTraversal.constant(e)

  def addInE(firstVertexKeyOrEdgeLabel: String, edgeLabelOrSecondVertexKey: String, propertyKeyValues: AnyRef*): GrafGraphTraversal[Start, Edge] =
    graphTraversal.addInE(firstVertexKeyOrEdgeLabel, edgeLabelOrSecondVertexKey, propertyKeyValues: _*)

  def range(low: Long, high: Long): GrafGraphTraversal[Start, End] =
    graphTraversal.range(low, high)

  def mean(): GrafGraphTraversal[Start, JDouble] =
    graphTraversal.mean()

  def branch[M, E2](branchTraversal: GrafTraversal[_, M]): GrafGraphTraversal[Start, E2] =
    graphTraversal.branch(branchTraversal).asInstanceOf[GraphTraversal[Start, E2]]

  def groupCount[E2]: GrafGraphTraversal[Start, JMap[E2, JLong]] =
    graphTraversal.groupCount().asInstanceOf[GraphTraversal[Start, JMap[E2, JLong]]]

  def groupCount(sideEffectKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.groupCount(sideEffectKey)

  def addV(kv: AnyRef, keyValues: AnyRef*): GrafGraphTraversal[Start, Vertex] =
    graphTraversal.addV(kv +: keyValues: _*)

  def aggregate(sideEffectKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.aggregate(sideEffectKey)

  def range[E2](scope: Scope, low: Long, high: Long): GrafGraphTraversal[Start, E2] =
    graphTraversal.range(scope, low, high).asInstanceOf[GraphTraversal[Start, E2]]

  def filter(filterTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.filter(filterTraversal)

  def as(stepLabel: String, stepLabels: String*): GrafGraphTraversal[Start, End] =
    graphTraversal.as(stepLabel, stepLabels: _*)

  def choose[E2](p: End ⇒ Boolean, t: GrafTraversal[_, E2], f: GrafTraversal[_, E2]): GrafGraphTraversal[Start, E2] =
    graphTraversal.choose(p, t, f)

  def not(notTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.not(notTraversal)

  def sack[E2](): GrafGraphTraversal[Start, E2] =
    graphTraversal.sack().asInstanceOf[GraphTraversal[Start, E2]]

  def coin(probability: Double): GrafGraphTraversal[Start, End] =
    graphTraversal.coin(probability)

  def limit(limit: Long): GrafGraphTraversal[Start, End] =
    graphTraversal.limit(limit)

  def choose[M, E2](choiceFunction: End ⇒ M): GrafGraphTraversal[Start, E2] =
    graphTraversal.choose(choiceFunction).asInstanceOf[GraphTraversal[Start, E2]]

  def fold[E2](seed: E2, fold: (E2, End) ⇒ E2): GrafGraphTraversal[Start, E2] =
    graphTraversal.fold(seed, fold)

  def min[E2 <: Number](scope: Scope): GrafGraphTraversal[Start, E2] =
    graphTraversal.min(scope).asInstanceOf[GraphTraversal[Start, E2]]

  def select[E2](selectKey: String): GrafGraphTraversal[Start, E2] =
    graphTraversal.select(selectKey).asInstanceOf[GraphTraversal[Start, E2]]

  def profile(): GrafGraphTraversal[Start, End] =
    graphTraversal.profile()

  def subgraph(sideEffectKey: String): GrafGraphTraversal[Start, Edge] =
    graphTraversal.subgraph(sideEffectKey)

  def filter(predicate: Traverser[End] ⇒ Boolean): GrafGraphTraversal[Start, End] =
    graphTraversal.filter(predicate)

  def until(untilTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.until(untilTraversal)

  def coalesce[E2](coalesceTraversals: GrafTraversal[_, E2]*): GrafGraphTraversal[Start, E2] =
    graphTraversal.coalesce(coalesceTraversals.map(toTraversal(_)): _*)

  def barrier(): GrafGraphTraversal[Start, End] =
    graphTraversal.barrier()

  def select[E2](pop: Pop, selectKey: String): GrafGraphTraversal[Start, E2] =
    graphTraversal.select(pop, selectKey).asInstanceOf[GraphTraversal[Start, E2]]

  def sample(amountToSample: Int): GrafGraphTraversal[Start, End] =
    graphTraversal.sample(amountToSample)

  def iterate(): GrafGraphTraversal[Start, End] =
    graphTraversal.iterate()

  def tail[E2](scope: Scope): GrafGraphTraversal[Start, E2] =
    graphTraversal.tail(scope).asInstanceOf[GraphTraversal[Start, E2]]

  def toV(direction: Direction): GrafGraphTraversal[Start, Vertex] =
    graphTraversal.toV(direction)

  def flatMap[E2](flatMapTraversal: GrafTraversal[_, E2]): GrafGraphTraversal[Start, E2] =
    graphTraversal.flatMap(flatMapTraversal)

  def addOutE(
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: AnyRef*): GrafGraphTraversal[Start, Edge] =
    graphTraversal.addOutE(firstVertexKeyOrEdgeLabel, edgeLabelOrSecondVertexKey, propertyKeyValues: _*)

  def count(): GrafGraphTraversal[Start, JLong] =
    graphTraversal.count()

  def by(byTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.by(byTraversal)

  def by(order: Order): GrafGraphTraversal[Start, End] =
    graphTraversal.by(order)

  def max[E2 <: Number](scope: Scope): GrafGraphTraversal[Start, E2] =
    graphTraversal.max(scope).asInstanceOf[GraphTraversal[Start, E2]]

  def addE(
    direction: Direction,
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: AnyRef*): GraphTraversal[Start, Edge] =
    graphTraversal.addE(direction, firstVertexKeyOrEdgeLabel, edgeLabelOrSecondVertexKey, propertyKeyValues)

  def by(tokenProjection: T): GrafGraphTraversal[Start, End] =
    graphTraversal.by(tokenProjection)

  def identity(): GrafGraphTraversal[Start, End] =
    graphTraversal.identity()

  def emit(): GrafGraphTraversal[Start, End] =
    graphTraversal.emit()

  def option[M, E2](pickToken: M, traversalOption: GrafTraversal[End, E2]): GrafGraphTraversal[Start, End] =
    graphTraversal.option(pickToken, traversalOption)

  def tail: GrafGraphTraversal[Start, End] =
    graphTraversal.tail()

  def order: GrafGraphTraversal[Start, End] =
    graphTraversal.order()

  def map[E2](function: Traverser[End] ⇒ E2): GrafGraphTraversal[Start, E2] =
    graphTraversal.map(function)

  def tail[E2](scope: Scope, limit: Long): GrafGraphTraversal[Start, E2] =
    graphTraversal.tail(scope, limit).asInstanceOf[GraphTraversal[Start, E2]]

  def fold(): GrafGraphTraversal[Start, JList[End]] =
    graphTraversal.fold()

  def sum(scope: Scope): GrafGraphTraversal[Start, JDouble] =
    graphTraversal.sum(scope)

  def emit(emitTraversal: GrafTraversal[_, _]): GrafGraphTraversal[Start, End] =
    graphTraversal.emit(emitTraversal)

  def toE(direction: Direction, edgeLabels: String*): GrafGraphTraversal[Start, Edge] =
    graphTraversal.toE(direction, edgeLabels: _*)

  def by[V](traversal: GrafTraversal[_, _], endComparator: Ordering[V]): GrafGraphTraversal[Start, End] =
    graphTraversal.by(traversal, endComparator)

  def by(elementPropertyKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.by(elementPropertyKey)

  def order(scope: Scope): GrafGraphTraversal[Start, End] =
    graphTraversal.order(scope)

  def limit[E2](scope: Scope, limit: Long): GrafGraphTraversal[Start, E2] =
    graphTraversal.limit(scope, limit).asInstanceOf[GraphTraversal[Start, E2]]

  def by(comparator: Ordering[End]): GrafGraphTraversal[Start, End] =
    graphTraversal.by(comparator)

  def dedup(scope: Scope, dedupLabels: String*): GrafGraphTraversal[Start, End] =
    graphTraversal.dedup(scope, dedupLabels: _*)

  def group(sideEffectKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.group(sideEffectKey)

  def dedup(dedupLabels: String*): GrafGraphTraversal[Start, End] =
    graphTraversal.dedup(dedupLabels: _*)

  def by[V](f: V ⇒ AnyRef): GrafGraphTraversal[Start, End] =
    graphTraversal.by(f)

  def mean(scope: Scope): GrafGraphTraversal[Start, JDouble] =
    graphTraversal.mean(scope)

  def `match`[E2](matchTraversals: GrafTraversal[_, _]*): GrafGraphTraversal[Start, JMap[String, E2]] =
    graphTraversal.`match`(matchTraversals.map(toTraversal(_)): _*).asInstanceOf[GraphTraversal[Start, JMap[String, E2]]]

  def choose[M, E2](choiceTraversal: GrafTraversal[_, M]): GrafGraphTraversal[Start, E2] =
    graphTraversal.choose(choiceTraversal).asInstanceOf[GraphTraversal[Start, E2]]

  def option[E2](traversalOption: GrafTraversal[End, E2]): GrafGraphTraversal[Start, End] =
    graphTraversal.option(traversalOption)

  def simplePath: GrafGraphTraversal[Start, End] =
    graphTraversal.simplePath()

  def sideEffect(consumer: Traverser[End] ⇒ Unit): GrafGraphTraversal[Start, End] =
    graphTraversal.sideEffect(consumer)

  def branch[M, E2](f: Traverser[End] ⇒ M): GrafGraphTraversal[Start, E2] =
    graphTraversal.branch((t: Traverser[End]) ⇒ f(t)).asInstanceOf[GraphTraversal[Start, E2]]

  def path: GrafGraphTraversal[Start, Path] =
    graphTraversal.path()

  def by[V](elementPropertyProjection: String, propertyValueComparator: Ordering[V]): GrafGraphTraversal[Start, End] =
    graphTraversal.by(elementPropertyProjection, propertyValueComparator)

  def barrier(maxBarrierSize: Int): GrafGraphTraversal[Start, End] =
    graphTraversal.barrier(maxBarrierSize)

  def where(startKey: String, predicate: P[String]): GrafGraphTraversal[Start, End] =
    graphTraversal.where(startKey, predicate)

  def store(sideEffectKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.store(sideEffectKey)

  def until(until: Traverser[End] ⇒ Boolean): GrafGraphTraversal[Start, End] =
    graphTraversal.until((u: Traverser[End]) ⇒ until(u))

  def unfold[E2](): GrafGraphTraversal[Start, E2] =
    graphTraversal.unfold().asInstanceOf[GraphTraversal[Start, E2]]

  def sack[V](sackFunction: (V, End) ⇒ V): GrafGraphTraversal[Start, End] =
    graphTraversal.sack(sackFunction)

  def sack[V](sackOperator: (V, V) ⇒ V, elementPropertyKey: String): GrafGraphTraversal[Start, End] =
    graphTraversal.sack(sackOperator, elementPropertyKey)

  def where(predicate: P[String]): GrafGraphTraversal[Start, End] =
    graphTraversal.where(predicate)
}
