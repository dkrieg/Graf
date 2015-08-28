package graf.tp3.docs

import graf.gremlin.structure.schema._
import graf.gremlin.structure.syntax._
import graf.gremlin.structure.util._
import graf.gremlin.structure.util.show._

import scalaz.Scalaz._

object TheGraphStructure extends App {
  val Person = Label("person")
  val Software = Label("software")
  val Knows = Label("knows")
  val Created = Label("created")
  val Name = Key[String]("name")
  val Age = Key[Int]("age")
  val Lang = Key[String]("lang")
  val Weight = Key[Float]("weight")

  val g = TinkerGraphFactory.open()
  val marko  = g + (Person,   ID(1), Name("marko"),  Age(29))
  val vadas  = g + (Person,   ID(2), Name("vadas"),  Age(27))
  val lop    = g + (Software, ID(3), Name("lop"),    Lang("java"))
  val josh   = g + (Person,   ID(4), Name("josh"),   Age(32))
  val ripple = g + (Software, ID(5), Name("ripple"), Lang("java"))
  val peter  = g + (Person,   ID(6), Name("peter"),  Age(35))

  marko --- (Knows,   ID(7),  Weight(0.5f)) --> vadas
  marko --- (Knows,   ID(8),  Weight(1.0f)) --> josh
  marko --- (Created, ID(9),  Weight(0.4f)) --> lop
  josh  --- (Created, ID(10), Weight(1.0f)) --> ripple
  josh  --- (Created, ID(11), Weight(0.4f)) --> lop
  peter --- (Created, ID(12), Weight(0.2f)) --> lop

  g.println
  g.close()
}