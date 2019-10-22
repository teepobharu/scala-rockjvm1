package lectures.part2oop

object OOBasics extends App{
  val person = new Person("John", 25)
  println(person)

  val author = new Writer("Charlse","Dickens", 1812)
  val imposter = new Writer("Charlse","Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))
  val counter = new Counter
  counter.inc(1)
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print

}

// class parameters are NOT FIELDS
// add val/var in front to convert to feilds

class Person(name: String, val age: Int = 0) { //constructor
  val x = 2
  println(1+3)
def greet(name: String): Unit = println(s"${this.name}Says : Hi $name")
  def greet():Unit = println(s"Hi, I am $name")

  // multiple constrictors - can be achieved through default parameters too
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

/*
 Novel and Writer
*/

class Writer(first: String, last: String, val year: Int){
  def fullName:String = s"$first, $last"

}
class Novel(name:String, year:Int, author:Writer){
  def authorAge = year - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear:Int) : Novel = new Novel(name, newYear, author)
}

class Counter(val count:Int = 0) {
  def inc = {
    println("increasing")
    new Counter(count + 1) // immutability
  }
  def dec =  {
    println("decreasing")
    new Counter(count - 1)
  }

  def dec(n:Int):Counter = {
    if( n<=0) this
    else dec.dec(n-1)
  }

  def inc(n:Int):Counter = {
    if(n <= 0) this
    else inc.inc(n-1)
  }

  def print = println(count)

}
