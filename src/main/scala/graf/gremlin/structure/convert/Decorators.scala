package graf.gremlin.structure.convert

trait Decorators {
  /** Generic class containing the `asJava` converter method */
  class AsJava[A](op: ⇒ A) {
    /** Converts a Scala collection to the corresponding Java collection */
    def asJava: A = op
  }

  /** Generic class containing the `asScala` converter method */
  class AsScala[A](op: ⇒ A) {
    /** Converts a Java collection to the corresponding Scala collection */
    def asScala: A = op
  }

}
object Decorators extends Decorators with Serializable
