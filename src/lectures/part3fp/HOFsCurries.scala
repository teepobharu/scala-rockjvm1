package lectures.part3fp

object HOFsCurries extends App {
//  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  //higher order function (HOF)
  // map, flatMap, filter in MyList
  // function that applies a function n times over a value x

  //function that applies a functinon n times over a value x
  // n Times(f,3,x)
  // n Times(f,3,x) = f(f(f(x)) = nTimes(f,2,f(x)) = f(f(f(x)))

  def nTimes(f:Int => Int, n: Int, x:Int):Int =
    if(n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x:Int) => x+1
  println(nTimes(plusOne, 10, 1))

    // ntb(f,n) = x => f(f(f...(x))
  def nTimesBetter(f:Int => Int, n:Int): (Int => Int) =
    if(n <= 0) (x:Int) => x
    else (x:Int) => nTimesBetter(f, n-1)(f(x))

  val plusTen = (x:Int) => x+10
  val tenTimesBetter = nTimesBetter(plusTen, 2)
  println(tenTimesBetter(1))


  val superAdder: Int => Int => Int = (x:Int) => (y:Int) => x + y
  // Right Associative Int => (Int => Int) = (x:Int) => (y:Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))

  //function with multiple parameter lists - CURRIED
  def curriedFormatter(c:String)(x: Double) = c.format(x)
  // need to provide the types else compiler will not pass OR use partial function applicatoins
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

}
