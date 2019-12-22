package lectures.part3fp

object TuplesAndMaps extends App{
//  tuples = finite orederd lists
//  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple = (2, "hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim",555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phonebook)
  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")))

  // mapValues
  println(phonebook.mapValues(number => "0245-"+number))

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555), ("asd","asdd")).toMap)
  val names = List("Bob", "James", "angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  val test = for{
    (k, v) <- phonebook
  } yield {
    println(k.toString + "-" +  v.toString)
    k.toString + "-" +  v.toString
  }

  println(test)
  println(phonebook.map{
    t=>
      val (e1, e2) = t
      Vector(e1.toString + e2)
  })
}
