package lectures.part2oop

object Generics extends App {

  class MyList[+A]{
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }
  class MyMap[Key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]
  // generic methods


  object MyList{
    //object cannot be type parameterize
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // Variance Problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // yes List[Cat] extends List [Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? Hard Question. => we returns list of Animals

  //2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
//  val invariantAnimalLists: InvariantList[Animal] = new InvariantList[Cat] //Error

  //3. Hell, no! Contravariance
  class ContravariantList[-A]
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] //scemantically more reasonble
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // bounded types
  //1. Lower bound Type [A <:] Animal
  //2. Upper bound Type [A >:] Animal
  class Cage[A >:Animal](animal: A)
  val cage = new Cage(new Dog)
  class Car
//  val newCage = new Cage(new Car)

  // expand MyList to be generic

}
