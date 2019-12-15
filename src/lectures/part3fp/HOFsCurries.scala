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
  /*
    1. Expand myList
      - foreach method A => Unit
        [1,2,3].foreach(x => println(x))

      - sort function ((A,A) => Int) => MyList
        [1,2,3].sort((x,y) => y-x) => [3,2,1]


      - zipWith (list, (A, A) => B) => MyList(B)
      [1,2,3].zipWith([4,5,6], x*y ) => [1*4, 2*5 , 3*6]

      - fold(start)(function) => a value
        [1,2,3].fold(0)(x+y) = 6

     2. toCurry(f: (Int,Int) => Int) => (Int => Int => Int)
        fromCurry(f: Int => Int => Int)) => (Int, Int) => Int

     3. compose(f,g) => x => f(g(x))
        andThen(f,g) => x => g(f(x))
   */
    // HOFS Exercise
    def toCurry(f: (Int, Int) => Int ): (Int => Int => Int) =
      x => y => f(x,y)
    def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
      (x,y) => f(x)(y)

    def compose[A,B,T](f: A => B, g: T => A): T => B =
      x => f(g(x))
    def andThen[A,B,C](f: A => B, g: B => C): A => C =
      x => g(f(x))
  // --- ---
    def superAdder2: (Int => Int => Int) = toCurry(_ + _)
    def add4 = superAdder2(2)
    println(add4(16))

    def simpleAdder = fromCurry(superAdder)
    println(simpleAdder(2,3))
    val add2 = (x:Int) => x+2
    val times3 = (x: Int) => x*3
    val composed = compose(add2, times3)
    val andThened = andThen(add2, times3)
    println(composed(2))
  println(andThened(2))
}
