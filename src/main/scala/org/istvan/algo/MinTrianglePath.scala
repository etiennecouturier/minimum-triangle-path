package org.istvan.algo

import java.lang.Math.{max, min}

import scala.io.Source.fromInputStream

object MinTrianglePath extends App {

  val t = readToList("org/istvan/algo/be_data_small.txt")
  val i = findSmallestSumPathLeafIndex(t.map(_.toArray).toArray)
  print(findPath(t.reverse, i).reverse)


  def readToList(fileName: String) =
    fromInputStream(MinTrianglePath.getClass
      .getResourceAsStream(fileName))
      .getLines
      .toList
      .map(_.split(" ")
        .toList.map(_.toInt))

  def findSmallestSumPathLeafIndex(triangle: Array[Array[Int]]) = {
    val dp = triangle.map(_.toArray)
    for (i <- 1 until triangle.length by 1) {
      for (j <- 0 to i)
        dp(i)(j) = min(
          triangle(i - 1)(max(0, j - 1)),
          triangle(i - 1)(min((triangle(i - 1).length) - 1, j))
        ) + triangle(i)(j)
    }
    dp(dp.length-1).zipWithIndex.min._2
  }

  def findPath(triangle: List[List[Int]], i: Int): List[Int] = triangle match {
    case Nil => Nil
    case h :: Nil => h(0) :: Nil
    case h :: t => h(i) :: findPath(t, if (t.head(max(0, i - 1)) < t.head(min(t.length - 1, i))) max(0, i - 1) else min(t.length - 1, i))
  }

}
