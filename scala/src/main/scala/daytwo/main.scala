package daytwo

import util.Util

@main
def main(): Unit = {

  val puzzleInput: List[String] = Util.puzzleInputToList("src/main/scala/daytwo/puzzle_input.txt")
  val parsedInput: List[Range] = puzzleInput.flatMap(lineToRanges)

  val invalidIds =
    parsedInput
      .map(_.subRanges)
      .flatMap(x => x.filter(isInvalidId))

  val sumOfInvalidIds = invalidIds.sum
  println("Part 1: " + sumOfInvalidIds)

  val part2Ranges = parsedInput.flatMap(_.subRanges)
  val result =
    part2Ranges
      .map(_.toString)
      .filter(_.matches(repeatingNumberRegex.regex))
      .map(_.toLong)
      .sum

  println("Part 2: " + result.toString)
}

val repeatingNumberRegex = "\\b(\\d+)\\1+\\b".r

final case class Range(start: String, end: String) {
  def subRanges: Seq[Long] = start.toLong to end.toLong
}

def lineToRanges(input: String): List[Range] = {
  val startAndFinish: Array[String] = input.split(",")
  startAndFinish.map { r =>
    val startAndEnd = r.split("-")
    Range(startAndEnd(0), startAndEnd(1))
  }.toList
}

def isInvalidId(id: Long): Boolean = {
  val idString = id.toString
  val midPoint = idString.length / 2
  if (idString.length % 2 == 0) {
    val start = idString.take(midPoint)
    val end = idString.takeRight(midPoint)
    start == end
  }
  else false
}