package dayfive

import util.Util
import scala.collection.immutable.NumericRange.Inclusive
import scala.annotation.tailrec

@main
def main(): Unit = {

  val puzzleInput             = Util.puzzleInputToList("src/main/scala/dayfive/puzzle_input.txt")
  val (ranges, ingredientIds) =
    puzzleInput
      .filter(_.contains("-"))
      .map(rangeStringToRange) -> puzzleInput.filterNot(_.contains("-")).filterNot(_.isBlank()).map(BigInt(_))
  println("Ranges: " + ranges)
  println("\n===\n")
  println("IngredientIds: " + ingredientIds)
  println("\n===\n")
  ingredientIds.map(id => ingredientIsFresh(id, ranges))
  println("Fresh ingredients count: " + count.toString())
  println("\n===\n")
  println("FreshId count from ranges:" + freshIdsFromRanges(ranges, BigInt(0)).toString())
  println("\n===\n")

}

var count = BigInt(0)

def rangeStringToRange(str: String): (BigInt, BigInt) = {
  val rangeList = str.split("-").toList
  BigInt(rangeList.head) -> BigInt(rangeList.last)
}

def ingredientIsFresh(id: BigInt, ranges: List[(BigInt, BigInt)]): Unit = {
  val idIsInRange = ranges
    .map { r =>
      id >= r._1 && id <= r._2
    }
    .filter(_ == true)
    .collect(_ == true)
    .nonEmpty
  if (idIsInRange) {
    count += 1
  }
}

@tailrec
def freshIdsFromRanges(ranges: List[(BigInt, BigInt)], acc: BigInt): BigInt = {
  ranges match
    case Nil          => acc
    case head :: Nil  => acc + checkRangeCount(head)
    case head :: next => freshIdsFromRanges(next, checkRangeCount(head))
}

def checkRangeCount(range: (BigInt, BigInt)): BigInt = {
  println("Checking range: " + range)

  val result = range._2 - range._1 + 1

  println("Result: " + result)
  result
}
