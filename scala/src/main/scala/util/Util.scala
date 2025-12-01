package util

import scala.annotation.tailrec
import scala.io.{BufferedSource, Source}

object Util {

  def puzzleInputToList(puzzleInputLocation: String): List[String] = {
    val file: BufferedSource = Source.fromFile(puzzleInputLocation)
    val lines: List[String] = file.getLines().toList
    file.close()
    lines
  }
  
  @tailrec
  def iterateRows[A, B](list: List[A], acc: List[B])(f: => A => List[B]): List[B] =
    list match
      case Nil => acc
      case ::(head, Nil) => acc
      case ::(head, next) => iterateRows(next, f(head))(f)

}
