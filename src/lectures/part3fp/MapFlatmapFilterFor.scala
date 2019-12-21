package lectures.part3fp

object MapFlatmapFilterFor extends App{
  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_+1))
  println(list.map(_+"hello"))

  //filter
  println(list.filter(_<3))

  //flatMap
  val toPair = (x:Int) => List(x, x+1)
  println(list.flatMap(toPair))

  //print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1", "a2", ... "d4")

  val combinations = numbers.filter(_%2==0)flatMap(n => chars.flatMap( m => colors.map(c => m+""+n+"-"+c)))
  println(combinations)

  //foreach
  list.foreach(println)

  //for-comprehensions : More readable than chaining on top
  val forCombinations = for {
    n <- numbers if n% 2 == 0 // guards (filter call)
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
 println(forCombinations)


  // syntax overload
  list.map { x =>
    x * 2
  }
  /*
    1. MyList supports for comprehensions?
    map(f: A => B) => MyList[b]
    filter(p: A => Boolean) => MyList[A]

    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
   */
}
