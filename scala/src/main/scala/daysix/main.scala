package daysix

import util.Util
import scala.annotation.tailrec

@main
def main(): Unit = {

  val puzzleInput = Util.puzzleInputToList("src/main/scala/daysix/puzzle_input.txt")

  @tailrec
  def iterate(originalInput: List[String], index: Int, acc: BigInt): BigInt = {
    if (index >= originalInput.last.trim.split("\\s{1,}").length) {
      println(s"Final acc: $acc")
      acc
    } else {
      println(s"Index: $index")
      println(s"Acc: $acc")
      val operand: String                       = originalInput.last.trim.split("\\s{1,}")(index)
      val numbersForColumnOfIndex: List[String] =
        originalInput
          .dropRight(1)
          .map(line => line.trim.split("\\s{1,}"))
          .map(_(index))
          .toList
      println(s"Numbers for column: ${numbersForColumnOfIndex.mkString(", ")}")

      val resultForColumn = operandToOperation(operand)(numbersForColumnOfIndex)
      val newAcc          = acc + resultForColumn
      println("Result for column: " + resultForColumn)
      println("New acc: " + newAcc)
      iterate(originalInput, index + 1, newAcc)
    }
  }

  def operandToOperation(operand: String)(numbers: List[String]): BigInt = {
    println(s"Operand: $operand")
    println(s"Numbers: $numbers")
    operand match {
      case "+" => numbers.map(BigInt(_)).sum
      case "*" => numbers.map(BigInt(_)).product
    }
  }

  val part1Answer: BigInt = iterate(puzzleInput, 0, 0)
  println(s"Part 1 answer: $part1Answer")
}
