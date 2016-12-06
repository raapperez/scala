
def factorial (x: Double) : Double = {
//  if(x == 0) 1 else x * factorial(x - 1)
  def factorialHelper (x:Double, acc: Double) : Double = {
    if(x == 0) acc else factorialHelper(x - 1, acc * x)
  }

  factorialHelper(x, 1)
}


factorial(0)
factorial(1)
factorial(2)
factorial(3)
factorial(170)
