package lectures.part2oop

object Objects extends App{
//  SCALA DOES NOT HAVE CLASS-LEVEL Functionality ("static")
  object Person { // type _ Its only instance
  // static/class - level functionality
    val N_EYES = 2
  def canFly: Boolean = false

  // factory method
  def apply(mother: Person, father: Person):Person = new Person("Bobbie")
  }
  class Person(val name:String){
    // instance-level functionality

  } // COMPANIONS
  println(Person.N_EYES)
  println(Person.canFly)

//  Scala object = SINGLETON INSTANCE

  val mary1 = Person
  val john1 = Person
  println (mary1 == john1)

  val john= new Person("John")
  val mary= new Person("Mary")
  println (john == mary)
  val bobbie = Person(mary, john)

  // Scala Application = Scala object with
  // def main(args: Array{string]):Unit
  println (Person.N_EYES)
}

