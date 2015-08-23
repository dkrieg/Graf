package graf.gremlin
package structure

import scala.collection.JavaConversions._


case class GrafVariables(private[structure] val variables: Variables) {
  def keys: Set[String] = variables.keys().toSet

  def remove(key: String): Unit = variables.remove(key)

  def asMap: Map[String, AnyRef] = variables.asMap().toMap

  def set(key: String, value: Any): Unit = variables.set(key, value)

  def get[R](key: String): Option[R] = variables.get(key).asScala
}
