package be.inniger.advent

class Day11 {

    companion object {
        private const val GRID_SIZE = 300
        private const val DEFAULT_SQUARE_SIZE = 3
    }

    fun solveFirst(serialNumber: Int) =
        solveGrid(serialNumber).simpleToString()

    fun solveSecond(serialNumber: Int) =
        solveGrid(serialNumber, (1..GRID_SIZE).toList()).fullToString()

    private fun solveGrid(serialNumber: Int, squareSizes: List<Int> = listOf(DEFAULT_SQUARE_SIZE)): SearchResult {
        val powers = Array(GRID_SIZE) { IntArray(GRID_SIZE) }

        for (x in 0 until GRID_SIZE) {
            for (y in 0 until GRID_SIZE) {
                powers[x][y] = calculateValue(x, y, serialNumber)
            }
        }

        return findHighestSquare(powers, squareSizes)
    }

    private fun calculateValue(x: Int, y: Int, serialNumber: Int): Int {
        val rackId = (x + 1) + 10 // Rack ID is the x coordinate plus 10
        var power = rackId * (y + 1) // Power level begin is rack ID times the y coordinate
        power += serialNumber // Add the serial number
        power *= rackId // Multiply by the rackId again
        power = (power / 100) % 10 // Only keep the hundreds digit
        power -= 5 // Subtract 5

        return power
    }

    private fun findHighestSquare(powers: Array<IntArray>, squareSizes: List<Int>): SearchResult {
        var highestPower = Int.MIN_VALUE
        var powerX = -1
        var powerY = -1
        var powerSquareSize = -1

        for (squareSize in squareSizes) {
            for (x in 0 until GRID_SIZE + 1 - squareSize) {
                for (y in 0 until GRID_SIZE + 1 - squareSize) {
                    var power = 0

                    for (innerX in x until x + squareSize) {
                        for (innerY in y until y + squareSize) {
                            power += powers[innerX][innerY]
                        }
                    }

                    if (power > highestPower) {
                        highestPower = power
                        powerX = x
                        powerY = y
                        powerSquareSize = squareSize
                    }
                }
            }
        }

        return SearchResult(powerX + 1, powerY + 1, powerSquareSize)
    }

    private data class SearchResult(val x: Int, val y: Int, val squareSize: Int = DEFAULT_SQUARE_SIZE) {
        fun simpleToString() = "$x,$y"

        fun fullToString() = "$x,$y,$squareSize"
    }
}
