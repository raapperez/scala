package week2

class Rational(x: Int, y: Int) {
  def n = x
  def d = y

  def  add(that: Rational) = new Rational(n * that.d + that.n * d, d * that.d)
  def  neg() = new Rational(-n, d)
  def  sub(that: Rational) = add(that.neg())

  override def toString = n + "/" + d
}
