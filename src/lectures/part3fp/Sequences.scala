package lectures.part3fp

import scala.util.Random

object Sequences extends App{
  // Seq
  val aSeq = Seq(1,3,2,4)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(7,5,6))
  println(aSeq.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 until 10 //to 1-10 or  until 1-9  - right or inclusive
  aRange.foreach(println)
  println(aRange)
  (1 to 2).foreach(_ => println("Hello 2 Times"))
  println(aRange(2))

  //Lists
  val aList = List(1,2,3)
//  val prepended = 42 :: aList
  val prepended = 42 +: aList :+ 3
  println(prepended)
  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(aList.mkString("-!-"))

  // aArays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3) // Default = null
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers /// implicit conversion Array -> Seq
  println(numbersSeq)

    // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  val maxRuns = 1000
  val maxCapacity = 1000000
  // vectors vs lists
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity),r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  // + keeps reference to tail
  // - updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // + Vector has to traverse the whole tree - depth of the tree is small
  // - needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
