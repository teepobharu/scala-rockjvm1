package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyList[B]
  def printElements: String
  //polymorphic call - toString is in every ref node so need override
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def add[B >: Nothing](element: B):MyList[B] = new Cons(element, Empty)
  def printElements:String = ""
  def isEmpty: Boolean = true
}

class Cons[+A](h: A, t:MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def add[B >:A](element: B):MyList[B] = new Cons(element, this)
  def isEmpty: Boolean = false
  def printElements: String =
    if(t.isEmpty) ""+h
    else h+ " " + t.printElements
}

object ListTest extends App {
//  val listOfIntegers: MyList[Int] = Empty
//  val listOfStrings: MyList[String] = Empty
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings = new Cons("Hello", new Cons("Scalla", Empty))

  println(listOfStrings.toString)
  println(listOfIntegers.toString)
}


