package exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >:A](element: B): MyList[B]
  def printElements: String
  //polymorphic call - toString is in every ref node so need override
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A,B]): MyList[B]
  def flatMap[B] (transformer: MyTransformer[A,MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]):MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
}
//21. - Add case keyword
case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def add[B >: Nothing](element: B):MyList[B] = new Cons(element, Empty)
  def printElements:String = ""
  def isEmpty: Boolean = true

  def map[B](transformer: MyTransformer[Nothing,B]): MyList[B] = Empty
  def flatMap[B] (transformer: MyTransformer[Nothing,MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]):MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
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
  def flatMap[B] (transformer: MyTransformer[A,MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

    /*
      [1,2].flatMap(n => [n+1]))
       = [1,2] ++ [2].flatMap(n=>[n, n+1])
       = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
       = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
     */

  def filter(predicate: MyPredicate[A]):MyList[A] =
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  /*
   [1,2,3].filter(n% 2 == 0)
   [2,3].filter(n% 2 == 0)
   [12,3].filter(n% 2 == 0)
   */

  def map[B](transformer: MyTransformer[A,B]): MyList[B] =
    new Cons(transformer.transform(h),t.map(transformer))
  /*
  [1,2,3].map(n*2)
    = new Cons(2, [2,3].map(n*2))
    = new Cons(2, new Cons(4, [3].map(n*2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty))
 */
}

object ListTest extends App {
//  val listOfIntegers: MyList[Int] = Empty
//  val listOfStrings: MyList[String] = Empty
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings = new Cons("Hello", new Cons("Scalla", Empty))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  println(listOfStrings.toString)
  println(listOfIntegers.toString)
  println(listOfIntegers.map(
    new MyTransformer[Int,Int] {
      override def transform(elem: Int): Int = elem * 2
    }
  ))
    println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(
    new MyTransformer[Int, MyList[Int]] {
      override def transform(elem: Int): MyList[Int] =
        new Cons(elem: Int, new Cons(elem + 1, Empty))
    }
  ))
  println(cloneListOfIntegers == listOfIntegers)

}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B]{
  def transform(elem: A): B
}

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



