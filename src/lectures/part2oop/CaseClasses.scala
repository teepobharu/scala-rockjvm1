package lectures.part2oop

object CaseClasses extends App {
/*
  equals, hashcode, toString
 */
  // try remove case see the behavior
 case class Person(name: String, age: Int)

  // 1. class parameters are fields
  var jim = new Person("Jim", 45)
  println(jim.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString) => syntatic sugar
  println(jim)
  //3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 45)
  println(jim == jim2)
  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  //5. CC2 have companion objects
  val thePerson = Person
  // only use this form when instantiate [no new key word]
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // Akka framework - sending serialize obejct through network

  // 7. CCs have extractor patterns = CCs can be used in aPATTERN MATCHING
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
  Expand MyList - use case classes and case object
   */


}

