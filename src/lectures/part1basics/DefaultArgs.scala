package lectures.part1basics

object DefaultArgs extends App{
  def trFact(n:Int, acc: Int = 1): Int =
    if(n <=1) acc
    else trFact(n-1, n*acc)
  val fact10 = trFact(10)
  println(fact10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int):Unit = println("saving picture width: " + width + " ,height:" + height)
  savePicture(width = 800, height = 2)
 /*
  1. pass in every loading arg
  2. name the args
  */
  savePicture(height = 600, width = 1)
}
