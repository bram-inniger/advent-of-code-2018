package be.inniger.advent

class Day10 {

    fun solveFirst(pointDescriptions: List<String>) =
        findMessage(pointDescriptions).message

    fun solveSecond(pointDescriptions: List<String>) =
        findMessage(pointDescriptions).time

    private fun findMessage(pointDescriptions: List<String>): MessageResult {
        val points = pointDescriptions.map { Point.from(it) }

        val minimumSpreadTime = findMinimumSpreadTime(points)
        val minimumSpreadPositions = positionsAt(minimumSpreadTime, points)
        val minimumSpreadView = viewFrom(minimumSpreadPositions)
        val message = prettyPrintPositions(minimumSpreadPositions, minimumSpreadView)

        return MessageResult(message, minimumSpreadTime)
    }

    // Theory: points converge into a message then diverge again, find the minimum spread to find the message
    private fun findMinimumSpreadTime(points: List<Point>): Int {
        var time = 0
        var previousSpread = Int.MAX_VALUE

        while (true) {
            val spread = viewFrom(positionsAt(time, points)).calculateSpread()

            if (spread > previousSpread) {
                return time - 1
            }

            previousSpread = spread
            time++
        }
    }

    private fun positionsAt(time: Int, points: List<Point>) =
        points.map {
            Position(
                it.initialPosition.xPosition + time * it.velocity.xVelocity,
                it.initialPosition.yPosition + time * it.velocity.yVelocity
            )
        }

    private fun viewFrom(positions: List<Position>): View {
        val xMin = positions.map { it.xPosition }.min()!!
        val xMax = positions.map { it.xPosition }.max()!!
        val yMin = positions.map { it.yPosition }.min()!!
        val yMax = positions.map { it.yPosition }.max()!!

        return View(Position(xMin, yMin), Position(xMax, yMax))
    }

    private fun prettyPrintPositions(positions: List<Position>, view: View): String {
        val minY = view.minimum.yPosition
        val minX = view.minimum.xPosition

        val height = view.maximum.yPosition - minY + 1
        val width = view.maximum.xPosition - minX + 1
        val message = Array(height) { CharArray(width) { '.' } }

        positions.forEach { message[it.yPosition - minY][it.xPosition - minX] = '#' }

        return message.map { it.joinToString("") }.joinToString(separator = "\n") { it }
    }

    private data class Point(internal val initialPosition: Position, internal val velocity: Velocity) {
        companion object {
            private val regex = """^position=<\s*(-?\d+),\s*(-?\d+)>\s*velocity=<\s*(-?\d+),\s*(-?\d+)>$""".toRegex()

            internal fun from(pointDescription: String): Point {
                val (xInitPos, yInitPos, xVelocity, yVelocity) =
                    regex.find(pointDescription)!!.destructured

                return Point(
                    Position(xInitPos.toInt(), yInitPos.toInt()),
                    Velocity(xVelocity.toInt(), yVelocity.toInt())
                )
            }
        }
    }

    private data class Position(internal val xPosition: Int, internal val yPosition: Int)

    private data class Velocity(internal val xVelocity: Int, internal val yVelocity: Int)

    private data class View(internal val minimum: Position, internal val maximum: Position) {

        internal fun calculateSpread() =
            (maximum.xPosition - minimum.xPosition) + (maximum.yPosition - minimum.yPosition)
    }

    private data class MessageResult(internal val message: String, internal val time: Int)
}
