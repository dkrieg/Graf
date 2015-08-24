package graf.gremlin
package process.traversal

import scala.language.implicitConversions

import org.apache.tinkerpop.gremlin.process.traversal.traverser.{
  TraverserRequirement ⇒ JTraverserRequirement
}

package object traverser {
  type TraverserRequirement = TraverserRequirement.TraverserRequirement

  object TraverserRequirement extends Enumeration {
    type TraverserRequirement = Value
    val OBJECT, BULK, SINGLE_LOOP, NESTED_LOOP, PATH, SACK, SIDE_EFFECTS = Value
  }

  implicit def toTraverserRequirement(d: JTraverserRequirement): TraverserRequirement = {
    import JTraverserRequirement._
    d match {
      case `OBJECT` ⇒ TraverserRequirement.OBJECT
      case `BULK` ⇒ TraverserRequirement.BULK
      case `SINGLE_LOOP` ⇒ TraverserRequirement.SINGLE_LOOP
      case `NESTED_LOOP` ⇒ TraverserRequirement.NESTED_LOOP
      case `PATH` ⇒ TraverserRequirement.PATH
      case `SACK` ⇒ TraverserRequirement.SACK
      case `SIDE_EFFECTS` ⇒ TraverserRequirement.SIDE_EFFECTS
    }
  }
  implicit def toJTraverserRequirement(d: TraverserRequirement): JTraverserRequirement = {
    import TraverserRequirement._
    d match {
      case `OBJECT` ⇒ JTraverserRequirement.OBJECT
      case `BULK` ⇒ JTraverserRequirement.BULK
      case `SINGLE_LOOP` ⇒ JTraverserRequirement.SINGLE_LOOP
      case `NESTED_LOOP` ⇒ JTraverserRequirement.NESTED_LOOP
      case `PATH` ⇒ JTraverserRequirement.PATH
      case `SACK` ⇒ JTraverserRequirement.SACK
      case `SIDE_EFFECTS` ⇒ JTraverserRequirement.SIDE_EFFECTS
    }
  }
}
