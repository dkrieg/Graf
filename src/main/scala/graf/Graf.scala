package graf

object Graf {
  def apply[A](action: ⇒ Graf[A]) = action
}

