package be.inniger.advent

class Day11 {

    companion object {
        private const val GRID_SIZE = 300
        private const val POWER_SQUARE_SIZE = 3
    }

    fun solveFirst(serialNumber: Int): String {
        val powers = Array(GRID_SIZE) { IntArray(GRID_SIZE) }

        for (x in 0 until GRID_SIZE) {
            for (y in 0 until GRID_SIZE) {
                powers[x][y] = calculateValue(x, y, serialNumber)
            }
        }

        return findHighestSquare(powers).toString()
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

    private fun findHighestSquare(powers: Array<IntArray>): Coordinate {
        var highestPower = Int.MIN_VALUE
        var powerX = -1
        var powerY = -1

        for (x in 0 until GRID_SIZE + 1 - POWER_SQUARE_SIZE) {
            for (y in 0 until GRID_SIZE + 1 - POWER_SQUARE_SIZE) {
                var power = 0

                for (innerX in x until x + POWER_SQUARE_SIZE) {
                    for (innerY in y until y + POWER_SQUARE_SIZE) {
                        power += powers[innerX][innerY]
                    }
                }

                if (power > highestPower) {
                    highestPower = power
                    powerX = x
                    powerY = y
                }
            }
        }

        return Coordinate(powerX + 1, powerY + 1)
    }

    private data class Coordinate(private val x: Int, private val y: Int) {

        override fun toString() = "$x,$y"
    }
}
