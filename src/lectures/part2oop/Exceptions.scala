package lectures.part2oop

object Exceptions extends App{
  val X:String = null
  // 1. thorwing exception
//  val aWeirdValue = throw new NullPointerException
  // throwwable classes extend the Throwable class.
  // exception and Error are the major throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 3
    case e: NullPointerException => println("null pointer exception"); println(e)
  } finally {
    // code that will get exceuted NO MATTER WHAT
    println("FINALLY")
  }
  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
  //  throw exception

  /*
  1. Crash with OutofMemoryError
  2. Crash with SOError
  3. PocketCal
    - add(x,y)
    - multiply(x,y)
    - divide(x,y)
    Throw
      - OverflowException exceeds Int.MAX_VALUE
      - UnderflowException subtract exceeds Int.MIN_VALUE
      - MathCalculationException divide by 0

   */
  // OOM
  //  val array = Array.ofDim(Int.MaxValue)
  // SO
  //  def infinite:Int = 1 + infinite
  //  val noLimit = infinite
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Can not divide by 0")

  object PocketCalculator {
    def add(x:Int, y: Int): Unit = {
      val result = x+y
      // positive + positive > Int.Max == Negative Int
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x:Int, y: Int): Unit = {
      val result = x-y
      if(x > 0 && y < 0 && result > 0) throw new OverflowException
      else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x:Int, y: Int): Unit = {
      val result = x*y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result < 0) throw new OverflowException
      else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
      else if(x > 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x:Int, y: Int): Unit = {
      if(y == 0) throw new MathCalculationException
      else x / y
    }
  }

  println(Int.MaxValue+10)
  println(Int.MinValue-10)
  println(PocketCalculator.add(Int.MaxValue, 10))

}
