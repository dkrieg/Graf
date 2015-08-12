package com.graf

import gremlin.scala.ScalaEdge

package object example {
  implicit class EdgeOps(e: ScalaEdge) {
    def weight(w: Double) = e.setProperty("weight", w)
  }

  def age(a: Int) = "age" -> a
  def lang(n: String) = "lang" -> n
  def name(n: String) = "name" -> n
  val person = "label" -> "person"
  val software = "label" -> "software"
  val knows = "knows"
  val created = "created"
}
