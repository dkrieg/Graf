package graf

import graf.gremlin.structure.schema._


package object example {
  val Person = Label("person")
  val Software = Label("software")
  val Knows = Label("knows")
  val Created = Label("created")
  val Age = Key[Int]("age")
  val Lang = Key[String]("lang")
  val Name = Key[String]("name")
  val YearCreated = Key[Int]("yearCreated")
  val Weight = Key[Double]("weight")
}
