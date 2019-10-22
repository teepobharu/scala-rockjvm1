package lectures.part2oop

object AbstractedDataTypes extends App{
  abstract class Animal {
    val creatureType: String = "Init"
    def eat: Unit
    def sleep:Unit = println("Test sleep Animal")
  }

  class Dog extends Animal{
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }
  // abstract class does not require onverride

  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  class Crocodile extends Animal with Carnivore{
    override val creatureType: String = "Croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal):Unit = s"I am a croc and I'm eating ${animal.creatureType}"
  }

  //  val unknown = new Animal

  val dog = new Dog
  val croc = new Crocodile
  croc eat dog
  dog.sleep
}
