package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App{
  class Person(val name: String, favouriteMovie: String, val age:Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def +(person:Person):String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname:String):Person= new Person(s"$name ($nickname)", favouriteMovie)
    def unary_! : String = s"$name, what the heck?!" // Remember to put the name between the mehotd or else compiler will think it was part of the method name
    def unary_+ : Person = new Person(name, favouriteMovie, age+1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"
    def apply(watchNum:Int): String = s"${this.name} watched $favouriteMovie $watchNum Times"
    def learns(thing: String) = s"$name is learning $thing"
    def learnScala() = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
//  INFIX notation = operation notaino (syntatic sugar)
  println(mary.likes("Inception"))
  println(mary likes "Inception") //Equiv

//  Operators in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))
  // PREFIX notation
  val x = -1
  val y = 1.unary_-
  println(y)
 // unary_prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!) // Equiv

  // POSTFIX notation - avail with method does not recieve parameters
  println(mary.isAlive) //normally use this one
  println(mary isAlive) // can cause ambiguity for code readers

  // apply
  println(mary.apply())
  println(mary()) // equiv - it will look at the class def of apply


  /*
  - Overload the + operator
  mary + "rockstar" => new person "Mary the rockstar"
  - Add an age to Person class
    Add unary _ p[eratpr => new person with the age + 1
    +mray => mary with the age incremeter
  3. Add a "learns: method in the Person class => "Mary learns Scala"
     Add a learnsScala method, calls learns method with "Scala"
  4. Overload the apply method
     mary.apply(2) => "Mary watched Inception 2 times
   */
  println((mary + "the Rockstar").apply())
  println(+mary age)
  println(mary learnScala)
  println(mary(2))

}

