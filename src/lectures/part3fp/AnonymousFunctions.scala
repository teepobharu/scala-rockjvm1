package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x*2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a:Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) //function itself
  println(justDoSomething()) //call

  // curly braces with lambdas - sometimes used
  val stringToInt = { (str: String) =>
    str.toInt
  }

  //MOAR synthetic sugar
  val niceIncrementer: Int => Int = _ + 2 // == x => x+1
  val niceAdder: (Int, Int) => Int = _ + _ // = (a,b) => a+ b
  /*
  1. MyList: replace all FunctionX calls with lamdas
  2. Rewrite the "special" adder as an anonymous function
   */
  val superAdd = (x:Int) => (y:Int) => x+ y
}
