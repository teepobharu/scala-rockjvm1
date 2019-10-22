package lectures.part1basics

object StringOps extends App{
  val str: String = "Hello this I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ '2')
  println(str.reverse)
  println(str.take(2))

//  S-interpolateors
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age"
  val anotherGreeting= s"Hello, my name is $name and I will be turining ${age+1} years old."
  println(anotherGreeting)
  //   F interpolators
  val speed = 1f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)

  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped") // injected variable get escaped




}
