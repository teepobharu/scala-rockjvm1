package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {
  //  tuples = finite orederd lists
  //  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple = (2, "hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  // Map Should be careful with mapping keys !!!
  val phonebook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 9000).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phonebook)
  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")))

  // mapValues
  println(phonebook.mapValues(number => "0245-" + number))

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555), ("asd", "asdd")).toMap)
  val names = List("Bob", "James", "angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  val test = for {
    (k, v) <- phonebook
  } yield {
    println(k.toString + "-" + v.toString)
    k.toString + "-" + v.toString
  }

  println(test)
  println(phonebook.map {
    t =>
      val (e1, e2) = t
      Vector(e1.toString + e2)
  })


  /*
  1. Original entryies "Jim" -> 555 and "JIM" -> 900
      !!! careful wuth mapping keys

    @2. Overly simplify social network based on maps
    Person = String
    - add to network
    - remove
    - friend (mutual)
    - unfriend

   */
  println(List(1, 2, 3) :+ List(1, 2));
List(1,2,3)+List(1,2).toString()
  //use set instead of list to guarantee list of friends contain UNIQUE element
  // + for set will return new set with element added (+: for List)
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }
  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB  = network(b)
    network + (a -> (friendsA - b )) + (b -> (friendsB - a))
  }
  // Remove friendship first before delete the person as a key
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))

  // Jim, Bob, Mary
  val emptyt: Map[String, Set[String]] = Map()
  val networkt = add(add(add(empty, "Bob"), "Mary"), "Jim")
  println(networkt)
  println(friend(networkt, "Bob", "Jim"))
  println(unfriend(friend(networkt, "Bob", "Jim"), "Bob", "Jim"))
  val network2friends = friend(friend(networkt, "Bob", "Mary"), "Bob", "Jim")

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size
  println(nFriends(network2friends, "Bob"));

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }
  println(mostFriends(network2friends));

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)
  println(nPeopleWithNoFriends(add(network2friends, "Lonely")));

  // if A knows B &  B knows C  ==  A has some social connection with C!
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    //breadth first search
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a)+a) // might be the case that might call connection with a and a
  }

  val networktt = add(networkt, "Cook")
  val testNet = friend(friend(friend(add(networktt, "Tim"), "Mary", "Jim"), "Jim", "Bob"), "Bob", "Cook");
  // Bob knows Marry and Jim but Mary Jim does not know each other but knows Bob
  println(socialConnection(network2friends, "Mary", "Jim"))
  println(socialConnection(testNet, "Mary", "Cook"))
  println(socialConnection(testNet, "Mary", "Tim"))
}
