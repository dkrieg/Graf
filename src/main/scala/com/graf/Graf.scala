package com.graf

object Graf {
  def apply[A](action: ⇒ Graf[A]) = action
}

