package s1

class Universe(_aliveCells: Set[(Int, Int)]) {
  val aliveCells = _aliveCells;
  val neighboursPosition = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

  type Cell = (Int, Int)

  def nextGen = {
    if (aliveCells.size < 2) new Universe(Set())

    def isCellAlive(cellWithCount: (Cell, Int)): Boolean = cellWithCount match {
      case (cell, count) => count == 2 || count == 3
    }

    val neighboursOfAliveCells: List[Cell] = aliveCells.toList.map { case cell => neighbours(cell) }.flatten
    val neighboursCount = neighboursOfAliveCells.groupBy(cell => cell).map { case (cell, listOfCells) => (cell, listOfCells.size) }
    val newAliveCells = neighboursCount.filter(isCellAlive).foldLeft(List[Cell]()) { (list, cellWithCount) => cellWithCount._1 :: list }
    new Universe(newAliveCells.toSet)
  }

  def neighbours(cell: Cell) =
    neighboursPosition.map { case (x, y) => (x + cell._1, y + cell._2) }
}