package daythree

import util.Util

@main
def main(): Unit = {

  val puzzleInput: List[String] = Util.puzzleInputToList("src/main/scala/daythree/puzzle_input.txt")

  val batteries: List[Bank] = puzzleInput.map { (r: String) =>
    val test: List[Int] = r.map((x: Char) => x.toString().toInt).toList
    Bank(test)
  }

  val values = batteries.map { (b: Bank) =>
    val indexes: List[(Int, Int)] = b.indexesOfTwoHighest
    val valuesWeWant: List[Int]   = indexes.map(i => b.batteries(i._1))
    val intValueOfValues: Int     = valuesWeWant.mkString.toInt
    println("Values: " + intValueOfValues)
    intValueOfValues
  }.sum

  println("Sum of values: " + values)
}

final case class Bank(batteries: List[Int]) {
  def findHighestInList(l: List[Int]) = l.sorted.takeRight(1).head
  def findIndexOf(in: Int): Int       = batteries.indexOf(in)
  def highestBatteryValue             = findHighestInList(batteries.dropRight(1))
  def indexOfHighest                  = findIndexOf(highestBatteryValue)
  type Value = Int
  type Index = Int
  def everythingAfterHighest                    = batteries.drop(indexOfHighest+1)
  def nextHighest                               = findHighestInList(everythingAfterHighest)
  def indexOfNextHighest                        = findIndexOf(nextHighest)
  def indexesOfTwoHighest: List[(Index, Value)] = List(
    indexOfHighest     -> highestBatteryValue,
    indexOfNextHighest -> nextHighest
  )

}
