package org.istvan.algo

import scala.collection.Iterator.continually
import scala.io.StdIn.readLine
import scala.math.Ordering.by
import scala.util.Try

case class PathWithCost(cost: Int, path: List[Int]) {
  def appendToPath(node: Int): PathWithCost = PathWithCost(cost + node, path :+ node)
}

object PathWithCost {
  implicit val ordering: Ordering[PathWithCost] = by(_.cost)
}

object MinTrianglePathEnhanced extends App {

  type Triangle = List[List[Int]]

  findMinPath.foreach(result => print(s"Minimal path is: ${result.path.mkString(" + ")} = ${result.cost}"))

  def findMinPath: Option[PathWithCost] =
    readFromConsoleToList.fold(
      e => {
        print("error occurred while processing the input\n")
        e.printStackTrace()
        None
      },
      inputList => if (inputList.nonEmpty) findPath(inputList) else None
    )

  private def readFromConsoleToList: Try[Triangle] = Try {
    continually(readLine)
      .takeWhile(_ != null) // :(
      .toList
      .map(_.split(" ")
        .toList.map(_.toInt))
  }

  def findPath(triangle: Triangle): Option[PathWithCost] = {
    triangle.map(_.map(Some(_)))
      .foldLeft(List.fill[Option[PathWithCost]](triangle.lastOption.map(_.length).getOrElse(0))(None))(
        (results, row) => updateRow(row, findMinimumParents(results))
      ).flatten.minOption
  }

  private def findMinimumParents(results: List[Option[PathWithCost]]): List[Option[PathWithCost]] =
    results.zipAll(None :: results.dropRight(1), None, None)
      .map {
        case (Some(left), Some(right)) => if (left.cost < right.cost) Some(left) else Some(right)
        case (None, Some(right)) => Some(right)
        case (Some(left), None) => Some(left)
        case _ => None
      }

  private def updateRow(row: List[Some[Int]], minParents: List[Option[PathWithCost]]): List[Option[PathWithCost]] =
    row.zipAll(minParents, None, None).map {
      case (Some(elem), Some(parent)) => Some(parent.appendToPath(elem))
      case (Some(elem), None) => Some(PathWithCost(elem, List(elem)))
      case _ => None
    }

}
