package dayfour

import util.Util
import scala.annotation.tailrec

@main
def main(): Unit = {

  val puzzleInput: List[String] = Util.puzzleInputToList("src/main/scala/dayfour/puzzle_input.txt")

  val emptyRow: String = {
    val lengthOfRow = puzzleInput.head.size + 2
    (1 to lengthOfRow).map(_ => ".").toList.mkString
  }
  val puzzleInputWithPadding                        = puzzleInput.map(r => "." + r + ".")
  val newInput                                      = List(emptyRow) ++ puzzleInputWithPadding ++ List(emptyRow)
  val inputZippedWithIndex: List[List[(Char, Int)]] = newInput.map(_.zipWithIndex.toList)

  println("Part 1: " + part1Iteration(inputZippedWithIndex, 0))

}

def printSplitter = println("\n======================\n")

def part1(
    rowAbove: List[(Char, Int)],
    currentRow: List[(Char, Int)],
    rowBelow: List[(Char, Int)]
): Int = {
  val x: List[Int] = currentRow.map { (i: (Char, Int)) =>
    val index = i._2
    val value = i._1
    if (value == '@') {
      val left          = currentRow(index - 1)._1
      val right         = currentRow(index + 1)._1
      val up            = rowAbove(index)._1
      val down          = rowBelow(index)._1
      val diagLeftUp    = rowAbove(index - 1)._1
      val diagRightUp   = rowAbove(index + 1)._1
      val diagLeftDown  = rowBelow(index - 1)._1
      val diagRightDown = rowBelow(index + 1)._1

      val adjacent: Int = List(
        left,
        right,
        up,
        down,
        diagLeftUp,
        diagRightUp,
        diagLeftDown,
        diagRightDown
      ).count(_ == '@')

      if (adjacent < 4) 1 else 0
    } else 0
  }

  x.count(_ == 1)

}

@tailrec
def part1Iteration(rows: List[List[(Char, Int)]], accumulator: Int): Int = {
  rows match {
    case Nil          => accumulator
    case head :: Nil  => accumulator
    case head :: tail =>
      val countForRow = part1(head, tail.head, tail.tail.headOption.getOrElse(List()))
      part1Iteration(
        tail,
        accumulator = accumulator + countForRow
      )
  }
}
