package lectures.part2oop

// import playground._ // import everything - use only when needed//
import java.sql.{Date => SqlDate}

import playground.{PrinceCharming, Cinderella => Princess}
/**
  * Created by Teepob.
  */

object PackagesAndImports extends App{
  // packages member are accessible by their simmple name
  val writer = new Writer("Daniel", "Wong", 1998)

  // import the package
//  val princess = new playground.Cinderella // fully qualified name
  val princess = new Princess
  val prince = new PrinceCharming

  // packages ar ein hierarchy
  //matching folder structure.

  //package object - make visible throughout the entire package
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val date = new Date
  val sqlDate = new SqlDate

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
