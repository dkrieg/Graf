package com.graf

import com.graf.gremlin.structure.schema._


package object example {
  val Person = Label("person")
  val Software = Label("software")
  val Knows = Label("knows")
  val Created = Label("created")
  object Age extends Key[Int]("age")
  object Lang extends Key[String]("lang")
  object Name extends Key[String]("name")
  object YearCreated extends Key[Int]("yearCreated")
  object Weight extends Key[Double]("weight")
}
