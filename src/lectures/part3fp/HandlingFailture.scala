package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailture extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAIL"))

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)
  
  val anotherPotentialFailture = Try {
    // code that might throw
  }

  println(potentialFailure.isSuccess)

  println("orElse")
  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
  println(fallbackTry)
  
  // If design API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_*2))
  println(aSuccess.flatMap(x => Success(x*10)))
  println(aSuccess.filter(_>10))

  // => for comprehensions
  /*
    Exercise
   */
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String):String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html> .... </html>"
      else throw new RuntimeException("Connection interrupted")
    }
    def getSafe(url: String): Try[String] = Try(get(url))

  }

  object HttpService {
    val random = new Random(System.nanoTime())
      def getConnection(host: String, port: String): Connection = {
        if(random.nextBoolean()) new Connection
        else throw new RuntimeException("Someone else took the port")
      }
    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // GET HTML
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe(("/home")))
    .foreach(renderHTML)
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  /*
    try {
    connection = HttpService.getConnection(host, port)
    try {
      page = ...
      renderHTML
      } catech (some other exception) {

      }
      } catch (exception) {

      }
   */
}
