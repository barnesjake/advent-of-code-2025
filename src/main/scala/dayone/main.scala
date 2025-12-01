package dayone

import util.Util

import scala.annotation.tailrec
import scala.util.Try

@main
def main(): Unit = {
  val puzzleInput: List[String] = Util.puzzleInputToList("src/main/scala/dayone/puzzle_input.txt")
  val parsedInput: List[Rotation] = puzzleInput.map(lineToRotation)
  val startingPoint: Int = 50
  val output: Int = iterateRows(parsedInput, startingPoint, 0)
  println(s"Output: $output")
}

final case class Rotation(direction: String, count: Int)

private def lineToRotation(inputString: String): Rotation = {
  val direction: String = Try(inputString.filterNot(_.isDigit)).getOrElse(throw new RuntimeException(s"Couldn't parse DIRECTION from input string: $inputString"))
  val count: Int = Try(inputString.filter(_.isDigit).toInt).getOrElse(throw new RuntimeException(s"Couldn't parse COUNT from input string: $inputString"))
  Rotation(direction, count)
}

private def maybeAddToTotal(position: Int): Boolean = position % 100 == 0

private def operation(point: Int, rotation: Rotation): (Int, Int) = {
  var tmpPoint = point
  var tmpAcc = 0
  for (n <- 0 until rotation.count) {
    rotation.direction match {
      case "L" =>
        tmpPoint -= 1
      case "R" =>
        tmpPoint += 1
    }
    if (tmpPoint % 100 == 0) {
      tmpAcc += 1
    }
  }
  tmpPoint -> tmpAcc
}

@tailrec
def iterateRows(restOfList: List[Rotation], position: Int, acc: Int): Int = {
  restOfList match
    case Nil =>
      acc
    case ::(head, Nil) =>
      val (updatedPosition, updatedAcc) = operation(position, head)
      acc + updatedAcc
    case ::(head, tail) =>
      val (updatedPosition, updatedAcc) = operation(position, head)
      val newTotal = acc + updatedAcc
      iterateRows(tail, updatedPosition, newTotal)
}
