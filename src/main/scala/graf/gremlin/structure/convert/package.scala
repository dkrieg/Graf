package graf.gremlin.structure

package object convert {
  val decorateAsJava  = new DecorateAsJava { }
  val decorateAsScala = new DecorateAsScala { }
  val decorateAll     = new DecorateAsJava with DecorateAsScala { }
  val wrapAsJava      = new WrapAsJava { }
  val wrapAsScala     = new WrapAsScala { }
  val wrapAll         = new WrapAsJava with WrapAsScala { }
}
