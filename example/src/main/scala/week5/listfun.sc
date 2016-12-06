object listfun {
  val nums = List(2, -4, 5, 7, 1)
  val fruits = List("apple", "pineapple", "orange", "banana")

  nums filter (x => x > 0)
  nums filterNot(x => x > 0)
  nums partition(x => x > 0)

  nums takeWhile(x => x > 0)
  nums dropWhile(x => x > 0)
  nums span (x => x > 0)

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, second) = xs1.span(e => e == x)
      List(x :: first) ::: pack(second)
  }

  def encode[T](xs: List[T]): List[(T, Int)] = pack(xs).map(x => (x.head, x.size))

  pack(List("a", "a", "a", "b", "c", "c", "a"))
  encode(List("a", "a", "a", "b", "c", "c", "a"))

}