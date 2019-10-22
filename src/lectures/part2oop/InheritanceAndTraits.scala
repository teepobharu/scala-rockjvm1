package lectures.part2oop

object InheritanceAndTraits extends App {

  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {

    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType  = "domestic"
    override def eat: Unit = print("crunchm crunch")
  }

  val dog = new Dog("testdog")

  dog.eat
  println(dog.creatureType)

  // type substitution (broad: POLYMORPHISM) - will go to the most recent override
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat
  println(unknownAnimal.creatureType)

  // overRIDING vs overLOADING
  // instances vs method
  class DogOriginal extends Animal {

  override def eat = super.eat
}
  val dogo = new DogOriginal;
  dogo.eat

  // SEAL
  sealed class Shape {
    val height = 0
    final val width = 10
  }
  class Triangle extends Shape {
    override val height = 2
//    override val width = 0
  }
  // FINAL
  final class House{
    val height = 100
  }
//  class Condo extends House {
//  }

  //preventing overrides
  // 1- use final on member
  // 2- use final on the entire class
  // 3- seal - class can not be used in other files
}
