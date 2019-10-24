package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }


  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahhaha")
  }

  /*
  equiv with === behind the scenes what compiler did
    class AnonymouseClasses$$anon$1 extends Animal {
    override def eat: Unit = println("anonymouse ahha")
  }
  val funnyAnimal: Animal = new AnonymouseClasses$$anon$1
   */
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name")
  }

  var jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim how can I get")
  }
  jim.sayHi
}
