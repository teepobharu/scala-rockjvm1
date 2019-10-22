
object Fact extends App{
  def fact(num:Int): Int =
    if(num<=1) 1
    else num*fact(num-1)
  println(2)
  println(fact(5))
}
