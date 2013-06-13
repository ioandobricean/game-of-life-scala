package ft-retreat

object GameOfLife {

  type Cell = (Int, Int)
  type Universe = Iterable[Cell]
  
  def nextAge(u: Universe): Universe = {
    newCells(u).union(aliveCells(u))
  }
  
  private def newCells(u: Universe): List[Cell] = {
    val (cells, _) = neighboursByCount(u).filter { case (_, size) => size == 3 }.unzip
    cells.toList
  }

  private def aliveCells(u: Universe): List[Cell] = {
    val (cells, _) = neighboursByCount(u).filter { case (_, size) => size == 3 || size == 2 }.unzip
    cells.toList.intersect(u.toList)
  }

  private def neighboursByCount(u: Universe) = {
    u.toList.map(cell => neighboursOfCell(cell)).flatten.groupBy(cell => cell).map { case (cell, list) => (cell, list.size) }
  }

  private def neighboursOfCell(cell: Cell): Seq[Cell] = {
    transitions.map { case (x, y) => (x + cell._1, y + cell._2) }
  }
  
  private def transitions: Seq[(Int, Int)] = {
    for {
      x <- -1 until 2
      y <- -1 until 2 
      if (x != 0 || y != 0)
    } yield (x, y)
  }

}