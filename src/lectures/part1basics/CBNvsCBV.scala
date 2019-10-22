package lectures.part1basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("by Value: " + x)
    println("by Value: " + x)
  }
  def calledByName(x: => Long): Unit = {
    println("by Value: " + x)
    println("by Value: " + x)
  }
  calledByValue(System.nanoTime()) // Use same number throughout the code
  calledByName(System.nanoTime()) // Get re-evaluate passed as function
  //
  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //  printFirst(infinite(),34) //Call Stack error
    printFirst(1, infinite()) // Survive b/c its not used

}
