package graf.gremlin
package process.traversal.dsl.graph

import org.apache.tinkerpop.gremlin.process.traversal.step.util.Tree
import org.apache.tinkerpop.gremlin.process.traversal._
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.{ __, GraphTraversal }
import org.apache.tinkerpop.gremlin.structure.VertexProperty.Cardinality
import org.apache.tinkerpop.gremlin.structure._
import structure.convert.wrapAll._
import structure.convert.decorateAll._
import java.lang.{ Long ⇒ JLong, Double ⇒ JDouble }
import java.util.{ Map ⇒ JMap, List ⇒ JList }

class GrafGraphTraversal[S, E](private[graph] override val traversal: GraphTraversal[S, E]) extends GrafTraversal[S, E](traversal) {
  def V: GrafVertexTraversal[Vertex] = __.start[Vertex]()

  def E: GrafEdgeTraversal[Edge] = __.start[Edge]()

  private def start[A]: GrafGraphTraversal[A, A] = __.start[A]()

  ///////////////////// MAP STEPS /////////////////////

  def mapT[E2](function: Traverser[E] ⇒ E2): GrafGraphTraversal[S, E2] =
    traversal.map(function)

  def map[E2](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.map(f(start))

  def flatMapT[E2](f: Traverser[E] ⇒ Iterator[E2]): GrafGraphTraversal[S, E2] =
    traversal.flatMap[E2]((t: Traverser[E]) ⇒ f(t).asJava)

  def flatMap[E2](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.flatMap(f(start))

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

  def `match`[E2](f: Seq[(GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, JMap[String, E2]] =
    traversal.`match`(f.map(t ⇒ toTraversal(t(start))): _*).asInstanceOf[GraphTraversal[S, JMap[String, E2]]]

  def sack[E2]: GrafGraphTraversal[S, E2] =
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

  def addV(keyValues: (Any, AnyRef)*): GrafVertexTraversal[S] =
    traversal.addV(keyValues.flatMap(p ⇒ Seq(p._1, p._2)).map(_.asInstanceOf[Object]): _*)

  def addE(
    direction: Direction,
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: (Any, AnyRef)*): GrafEdgeTraversal[S] =
    traversal.addE(
      direction,
      firstVertexKeyOrEdgeLabel,
      edgeLabelOrSecondVertexKey,
      propertyKeyValues.flatMap(p ⇒ Seq(p._1, p._2)).map(_.asInstanceOf[Object]): _*)

  def addOutE(
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: (Any, AnyRef)*): GrafEdgeTraversal[S] =
    traversal.addOutE(
      firstVertexKeyOrEdgeLabel,
      edgeLabelOrSecondVertexKey,
      propertyKeyValues.flatMap(p ⇒ Seq(p._1, p._2)).map(_.asInstanceOf[Object]): _*)

  def addInE(
    firstVertexKeyOrEdgeLabel: String,
    edgeLabelOrSecondVertexKey: String,
    propertyKeyValues: (Any, AnyRef)*): GrafEdgeTraversal[S] =
    traversal.addInE(
      firstVertexKeyOrEdgeLabel,
      edgeLabelOrSecondVertexKey,
      propertyKeyValues.flatMap(p ⇒ Seq(p._1, p._2)).map(_.asInstanceOf[Object]): _*)

  ///////////////////// FILTER STEPS /////////////////////

  def filterT(p: Traverser[E] ⇒ Boolean): GrafGraphTraversal[S, E] =
    traversal.filter(p)

  def filter(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.filter(f(start))

  def or(fs: Seq[(GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, E] =
    traversal.or(fs.map(t ⇒ toTraversal(t(start))): _*)

  def and(fs: Seq[(GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _])]): GrafGraphTraversal[S, E] =
    traversal.and(fs.map(t ⇒ toTraversal(t(start))): _*)

  def inject(i: E, injections: E*): GrafGraphTraversal[S, E] =
    traversal.inject(i +: injections: _*)

  def dedup: GrafGraphTraversal[S, E] = traversal.dedup()

  def dedup(dl: String, dedupLabels: String*): GrafGraphTraversal[S, E] =
    traversal.dedup(dl +: dedupLabels: _*)

  def dedup(scope: Scope, dedupLabels: String*): GrafGraphTraversal[S, E] =
    traversal.dedup(scope, dedupLabels: _*)

  def where(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.where(f(start))

  def where(startKey: String, predicate: P[String]): GrafGraphTraversal[S, E] =
    traversal.where(startKey, predicate)

  def where(predicate: P[String]): GrafGraphTraversal[S, E] =
    traversal.where(predicate)

  def has(propertyKey: String, f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.has(propertyKey, f(start))

  def is(predicate: P[E]): GrafGraphTraversal[S, E] =
    traversal.is(predicate)

  def is(value: Any): GrafGraphTraversal[S, E] =
    traversal.is(value)

  def not(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.not(f(start))

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

  def sideEffect(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.sideEffect(f(start))

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

  def branch[M, E2](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, M]): GrafGraphTraversal[S, E2] =
    traversal.branch(f(start)).asInstanceOf[GraphTraversal[S, E2]]

  def choose[M, E2](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, M]): GrafGraphTraversal[S, E2] =
    traversal.choose(f(start)).asInstanceOf[GraphTraversal[S, E2]]

  def choose[E2](
    p: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _],
    t: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2],
    f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.choose(p(start), t(start), f(start))

  def chooseP[E2](
    p: E ⇒ Boolean,
    t: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2],
    f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.choose(p, t(start), f(start))

  def chooseF[M, E2](choiceFunction: E ⇒ M): GrafGraphTraversal[S, E2] =
    traversal.choose(choiceFunction).asInstanceOf[GraphTraversal[S, E2]]

  def union[E2](
    fs: Seq[(GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2])]): GrafGraphTraversal[S, E2] =
    traversal.union(fs.map(t ⇒ toTraversal(t(start))): _*)

  def coalesce[E2](
    fs: Seq[(GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2])]): GrafGraphTraversal[S, E2] =
    traversal.coalesce(fs.map(t ⇒ toTraversal(t(start))): _*)

  def repeat(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E]): GrafGraphTraversal[S, E] =
    traversal.repeat(f(start))

  def emit: GrafGraphTraversal[S, E] = traversal.emit()

  def emitT(emitPredicate: Traverser[E] ⇒ Boolean): GrafGraphTraversal[S, E] =
    traversal.emit(emitPredicate)

  def emit(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.emit(f(start))

  def untilT(until: Traverser[E] ⇒ Boolean): GrafGraphTraversal[S, E] =
    traversal.until((u: Traverser[E]) ⇒ until(u))

  def until(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.until(f(start))

  def times(maxLoops: Int): GrafGraphTraversal[S, E] =
    traversal.times(maxLoops)

  def local[E2](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, E2]): GrafGraphTraversal[S, E2] =
    traversal.local(f(start))

  ///////////////////// UTILITY STEPS /////////////////////

  def as(stepLabel: String, stepLabels: String*): GrafGraphTraversal[S, E] =
    traversal.as(stepLabel, stepLabels: _*)

  def barrier: GrafGraphTraversal[S, E] =
    traversal.barrier()

  def barrier(maxBarrierSize: Int): GrafGraphTraversal[S, E] =
    traversal.barrier(maxBarrierSize)

  def by: GrafGraphTraversal[S, E] =
    traversal.by()

  def byE[V](element: Element ⇒ V, comparator: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(element, comparator)

  def by(order: Order): GrafGraphTraversal[S, E] =
    traversal.by(order)

  def by(tokenProjection: T): GrafGraphTraversal[S, E] =
    traversal.by(tokenProjection)

  def by(f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _]): GrafGraphTraversal[S, E] =
    traversal.by(f(start))

  def by[V](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[_, _], endOrdering: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(f(start), endOrdering)

  def by(elementPropertyKey: String): GrafGraphTraversal[S, E] =
    traversal.by(elementPropertyKey)

  def by(comparator: Ordering[E]): GrafGraphTraversal[S, E] =
    traversal.by(comparator)

  def byF[V](f: V ⇒ AnyRef): GrafGraphTraversal[S, E] =
    traversal.by(f)

  def by[V](elementPropertyProjection: String, propertyValueComparator: Ordering[V]): GrafGraphTraversal[S, E] =
    traversal.by(elementPropertyProjection, propertyValueComparator)

  def option[M, E2](
    pickToken: M,
    f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[E, E2]): GrafGraphTraversal[S, E] =
    traversal.option(pickToken, f(start))

  def option[E2](f: GrafGraphTraversal[_, _] ⇒ GrafTraversal[E, E2]): GrafGraphTraversal[S, E] =
    traversal.option(f(start))

  def iterate(): GrafGraphTraversal[S, E] =
    traversal.iterate()

  def id: GrafGraphTraversal[S, AnyRef] =
    traversal.id()

  def key: GrafGraphTraversal[S, String] = traversal.key()

  def label: GrafGraphTraversal[S, String] = traversal.label()

  def mapKeys[E2]: GrafGraphTraversal[S, E2] =
    traversal.mapKeys().asInstanceOf[GraphTraversal[S, E2]]

  def mapValues[E2]: GrafGraphTraversal[S, E2] =
    traversal.mapValues().asInstanceOf[GraphTraversal[S, E2]]

  def properties[E2]: GrafGraphTraversal[S, _ <: Property[E2]] =
    traversal.properties().asInstanceOf[GraphTraversal[S, _ <: Property[E2]]]

  def properties[E2](pk: String, propertyKeys: String*): GrafGraphTraversal[S, _ <: Property[E2]] =
    traversal.properties(pk +: propertyKeys: _*).asInstanceOf[GraphTraversal[S, _ <: Property[E2]]]

  def property(cardinality: Cardinality, key: String, value: Any, keyValues: AnyRef*): GrafGraphTraversal[S, E] =
    traversal.property(cardinality, key, value, keyValues: _*)

  def property(key: String, value: Any, keyValues: AnyRef*): GrafGraphTraversal[S, E] =
    traversal.property(key, value, keyValues: _*)

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
