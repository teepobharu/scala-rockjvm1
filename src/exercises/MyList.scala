package exercises

import scala.annotation.tailrec

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyList[B]
  def printElements: String
  //polymorphic call - toString is in every ref node so need override
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A=>B): MyList[B]
  def flatMap[B] (transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean):MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def forEach(f: A => Unit): Unit
  def sort(compare: (A,A) => Int): MyList[A]
  def zipwith[B,C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B,A) => B):B

}

//21. - Add case keyword
case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def add[B >: Nothing](element: B):MyList[B] = new Cons(element, Empty)
  def printElements:String = ""
  def isEmpty: Boolean = true

    // higher-order-function
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B] (transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean):MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // hofs
  def forEach(f: Nothing => Unit): Unit = {}
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipwith[B,C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have same length !")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B):B = start
}

case class Cons[+A](h: A, t:MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def add[B >:A](element: B):MyList[B] = new Cons(element, this)
  def isEmpty: Boolean = false
  def printElements: String =
    if(t.isEmpty) ""+h
    else h+ " " + t.printElements
  // Exercise 20.
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2]++[3,4,5])
    = new Cons(1, new Cons(2, Empty ++[3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  def flatMap[B] (transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

    /*
      [1,2].flatMap(n => [n+1]))
       = [1,2] ++ [2].flatMap(n=>[n, n+1])
       = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
       = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
     */

//  36. Change : def filter(predicate: MyPredicate[A]):MyList[A] =
  def filter(predicate: A => Boolean):MyList[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  /*
   [1,2,3].filter(n% 2 == 0)
   [2,3].filter(n% 2 == 0)
   [12,3].filter(n% 2 == 0)
   */

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h),t.map(transformer))
  /*
  [1,2,3].map(n*2)
    = new Cons(2, [2,3].map(n*2))
    = new Cons(2, new Cons(4, [3].map(n*2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty))
 */

  def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

// HOFs Exercise
  //  @tailrec
  def sort(compare: (A,A) => Int): MyList[A] = {
    // not tail recursive
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if(sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x,sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
  def zipwith[B,C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same length!")
    else new Cons(zip(h,list.head), t.zipwith(list.tail, zip))
  }
  def fold[B](start: B)(operator: (B,A) => B):B = {
      t.fold(operator(start, h))(operator)
    /*
    [1,2,3].fold(0)(+)
      = [2,3].fold(1)(+)
      = [3].fold(3)(+)
      = [].fold(6) //Empty case
      = 6
     */
  }

}

object ListTest extends App {
//  val listOfIntegers: MyList[Int] = Empty
//  val listOfStrings: MyList[String] = Empty
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings = new Cons("Hello", new Cons("Scalla", new Cons("Ahaa", Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  println(listOfStrings.toString)
  println(listOfIntegers.toString)

// Clip 36 Refactor to Functional Programming change =>  println(listOfIntegers.map(
//    new MyTransformer[Int,Int] {
//      override def transform(elem: Int): Int = elem * 2
//    }
//  ))

//  println(listOfIntegers.map( elem => elem *2).toString) Works too
  println(listOfIntegers.map( _ *2).toString) //Shorter
  println(listOfIntegers.filter(_%2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(elem => new Cons(elem: Int, new Cons(elem + 1, Empty))))
//  println(listOfIntegers.flatMap(new Cons(_ , new Cons(_ + 1, Empty)))) // DOES NOT WORK SINCE _ used 2 times 2nd '_' will refer to another args

  println(cloneListOfIntegers == listOfIntegers)

  // HOFS
  listOfIntegers.forEach(println)
  println(listOfIntegers.sort((x,y) => y-x))
  println(listOfIntegers.zipwith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(6)(_+_))

  // for comprehensions compatible since we have map flatMap filter function in MyList
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combinations)
  println(for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string)

}

// CLIP-36 DELETED
//trait MyPredicate[-T] { // T => Boolean
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B]{ // A => B
//  def transform(elem: A): B
//}

/*
  1. Generic trait MyPredicate[T]
  2. Generic trait MyTransformer[A,B] with method transform(A) => B
  3. MyList:
   - map(transformer) => MyList
   - filter(predicate) => MyList
   - flatMap(transfromer from A to MyList[B]) => MyList[B]

   class EvenPredicate extends MyPredicate[Int]
   class StringToIntTransfomer extends MyTransfoer[String, Int]

   [1,2,3].map(n*2) = [2,4,6]
   [1,2,4,5].filter(n%2) = [2,4]
   [1,2,3].flatMap(n=>[n,n+1]) => [1,2,2,3,3,4]
 */



