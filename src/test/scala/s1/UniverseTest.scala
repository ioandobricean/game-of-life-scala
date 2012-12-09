package s1

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UniverseTest extends FunSuite {

  test("empty universe has no alive cells")(
    assert(new Universe(Set()).aliveCells === Set())
  )

  test("init universe with one alive cell") {
    val aliveCells = Set((1, 1))
    val universe = new Universe(aliveCells)
    assert(universe.aliveCells === aliveCells)
  }

  test("universe with one alive cell is empty at next generation") {
    val aliveCells = Set((1, 1))
    val universe = new Universe(aliveCells).nextGen
    assert(universe.aliveCells === Set())
  }

  /* x00    0x0
   * 0x0 -> xxx
   * 00x    0x0
   */
  test("universe with 3 alive cells on primary diagonal has one cell at next generation") {
    val aliveCells = Set((1, 1), (2, 2), (3, 3))
    val universe = new Universe(aliveCells).nextGen
    assert(universe.aliveCells === Set((2, 1), (1, 2), (2, 2), (3, 2), (2, 3)))
  }

  /* 00x    0x0
   * 0x0 -> xxx
   * x00    0x0
   */
  test("universe with 3 alive cells on secondary diagonal has one cell at next generation") {
    val aliveCells = Set((1, 3), (2, 2), (3, 1))
    val universe = new Universe(aliveCells).nextGen
    assert(universe.aliveCells === Set((2, 1), (1, 2), (2, 2), (3, 2), (2, 3)))
  }

  /* 0x0    xxx
   * 0xx -> xxx
   * 000    0xx
   */
  test("a dead cell with 3 neigbours will became alive") {
    val aliveCells = Set((1, 1), (2, 1), (2, 2))
    val universe = new Universe(aliveCells).nextGen
    assert(universe.aliveCells === Set((1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2), (3, 1), (3, 2)))
  }

}