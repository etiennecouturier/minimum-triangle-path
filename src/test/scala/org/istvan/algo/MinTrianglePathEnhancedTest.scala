package org.istvan.algo

import org.scalatest.FunSuite

class MinTrianglePathEnhancedTest extends FunSuite {

  test("min triangle path test") {
    assert(MinTrianglePathEnhanced.findPath(List(
      List(2),
      List(3, 4),
      List(6, 5, 7),
      List(4, 1, 8, 3),
    )) === Some(PathWithCost(11, List(2, 3, 5, 1))))
  }

  test("min triangle path sample test provided in pdf") {
    assert(MinTrianglePathEnhanced.findPath(List(
      List(7),
      List(6, 3),
      List(3, 8, 5),
      List(11, 2, 10, 9),
    )) === Some(PathWithCost(18, List(7, 6, 3, 2))))
  }

  test("min triangle path empty input list") {
    assert(MinTrianglePathEnhanced.findPath(List()) === None)
  }

}
