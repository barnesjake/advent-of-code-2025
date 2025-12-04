error id: file://<WORKSPACE>/src/main/scala/daythree/main.scala:daythree/Bank#indexOfHighestOrdered().
file://<WORKSPACE>/src/main/scala/daythree/main.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -b/indexOfHighestOrdered.
	 -b/indexOfHighestOrdered#
	 -b/indexOfHighestOrdered().
	 -scala/Predef.b.indexOfHighestOrdered.
	 -scala/Predef.b.indexOfHighestOrdered#
	 -scala/Predef.b.indexOfHighestOrdered().
offset: 751
uri: file://<WORKSPACE>/src/main/scala/daythree/main.scala
text:
```scala
package daythree

import util.Util

@main
def main(): Unit = {

  val puzzleInput: List[String] = Util.puzzleInputToList("src/main/scala/daythree/puzzle_input.txt")

  val batteries: List[Bank] = puzzleInput.map { (r: String) =>
    val test: List[Int] = r.map((x: Char) => x.toString().toInt).toList
    Bank(test)
  }

  batteries.foreach((b: Bank) =>
    println("Battery: " + b.toString)
    println("1st value: " + b.highestBatteryValue)
    println("2nd value: " + b.nextHighest)
    println("Index of 1st value: " + b.indexOfHighest)
    println("Index of 2nd value: " + b.indexOfNextHighest)
    println("1st and 2nd: " + b.indexesOfTwoHighest)
  )

  val values = batteries.map { (b: Bank) =>
    val indexes: List[Int]      = b.indexOfHighes@@tOrdered
    val valuesWeWant: List[Int] = indexes.map(i => b.batteries(i))
    val intValueOfValues: Int   = valuesWeWant.mkString.toInt
    println("Values: " + intValueOfValues)
    intValueOfValues
  }.sum

  println("Sum of values: " + values)
}

final case class Bank(batteries: List[Int]) {
  def findHighestInList(l: List[Int]) = l.sorted.takeRight(1).head
  def findIndexOf(in: Int): Int       = batteries.indexOf(in)
  def highestBatteryValue             = findHighestInList(batteries)
  def indexOfHighest                  = findIndexOf(highestBatteryValue)
  type Value = Int
  type Index = Int
  def everythingAfterHighest                    = batteries.drop(indexOfHighest)
  def nextHighest                               = findHighestInList(everythingAfterHighest)
  def indexOfNextHighest                        = findIndexOf(nextHighest)
  def indexesOfTwoHighest: List[(Index, Value)] = List(
    indexOfHighest     -> highestBatteryValue,
    indexOfNextHighest -> nextHighest
  )

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 