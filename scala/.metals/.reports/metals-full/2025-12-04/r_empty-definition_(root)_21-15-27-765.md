error id: file://<WORKSPACE>/src/main/scala/daythree/main.scala:daythree/Bank#highestBatteryValues.
file://<WORKSPACE>/src/main/scala/daythree/main.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -highestBatteryValues.
	 -scala/Predef.highestBatteryValues.
offset: 1344
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
    println("Two highest values: " + b.highestBatteryValues)
    println("Highest value: " + b.highestValue)
    println("Second highest value: " + b.secondHighestValue)
    println("Index of highest value: " + b.findIndexOf(b.highestValue.head))
    println("Index of second highest value: " + b.findIndexOf(b.secondHighestValue.head))
    println("Two highest indexes: " + b.indexesOfTwoHighest)
    println("indexes ordered: " + b.indexOfHighestOrdered)
  )

  val values = batteries.map { (b: Bank) =>
    val indexes: List[Int]      = b.indexOfHighestOrdered
    val valuesWeWant: List[Int] = indexes.map(i => b.batteries(i))
    val intValueOfValues: Int   = valuesWeWant.mkString.toInt
    println("Values: " + intValueOfValues)
    intValueOfValues
  }.sum

  println("Sum of values: " + values)
}

final case class Bank(batteries: List[Int]) {
  val highestBatteryValue      = batteries.sorted.takeRight(1)
  val highestValue              = hi@@ghestBatteryValues.takeRight(1)
  val secondHighestValue        = highestBatteryValues.take(1)
  def findIndexOf(in: Int): Int = batteries.indexOf(in)
  type Value = Int
  type Index = Int
  def indexesOfTwoHighest: List[(Value, Index)] = List(
    highestValue.head       -> findIndexOf(highestValue.head),
    secondHighestValue.head -> findIndexOf(secondHighestValue.head)
  )
  def indexOfHighestOrdered: List[Int] = indexesOfTwoHighest.map(_._2).sorted

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 