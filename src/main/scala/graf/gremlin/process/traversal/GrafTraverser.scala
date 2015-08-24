package graf.gremlin
package process.traversal

class GrafTraverser[T](private[traversal] val traverser: Traverser[T]) {
  def sack[S](): S = traverser.sack()

  def compareTo(other: GrafTraverser[T]): Int = traverser.compareTo(other.traverser)

  def bulk(): Long = traverser.bulk()

  def path[A](stepLabel: String): A = traverser.path(stepLabel)

  def sack[S](s: S): Unit = traverser.sack(s)

  def asAdmin(): GrafTraverserAdmin[T] = traverser.asAdmin()

  def sideEffects(sideEffectKey: String, sideEffectValue: Any): Unit = traverser.sideEffects(sideEffectKey, sideEffectValue)

  def loops(): Int = traverser.loops()

  def get(): T = traverser.get()

//  def path[A](pop: Pop, stepLabel: String): A = traverser.path(pop, stepLabel) TODO

//  def path(): Path = traverser.path() TODO

  def sideEffects[A](sideEffectKey: String): A = traverser.sideEffects(sideEffectKey)
}
