package be.inniger.advent

import kotlin.math.abs

class Day06 {

    fun solveFirst(inputCoordinates: List<String>): Int {
        val points = pointsFromInput(inputCoordinates)
        val view = View.fromPoints(points)
        val coordinates = coordinatesFromView(view)

        return coordinates
            .mapNotNull { locate(it, points) }
            .groupBy { it.nearest }
            .values
            .mapNotNull { getSizeOfGroup(it, view) }
            .max()!!
    }

    fun solveSecond(inputCoordinates: List<String>, upperLimit: Int): Int {
        val points = pointsFromInput(inputCoordinates)
        val view = View.fromPoints(points)
        val coordinates = coordinatesFromView(view)

        return coordinates.map { distanceToAllPoints(it, points) }
            .filter { it < upperLimit }
            .count()
    }

    private fun pointsFromInput(inputCoordinates: List<String>) = inputCoordinates.indices
        .map { Coordinate.from(inputCoordinates[it]) }

    private fun coordinatesFromView(view: View): List<Coordinate> {
        return (view.minX..view.maxX)
            .flatMap { x ->
                (view.minY..view.maxY)
                    .map { y -> x to y }
            }
            .map { Coordinate.from(it) }
    }

    private fun locate(coordinate: Coordinate, points: List<Coordinate>): LocatedCoordinate? =
        points.groupBy { coordinate.manhattanDistanceTo(it) }
            .minBy { it.key }!!
            .value
            .singleOrNull()
            .let { if (it != null) LocatedCoordinate(coordinate, it) else null }

    // Infinite groups should be ignored and are thus get a null size
    private fun getSizeOfGroup(coordinates: List<LocatedCoordinate>, view: View) =
        if (coordinates.any { belongsToInfiniteGroup(it.coordinate, view) }) null
        else coordinates.size

    // If a coordinate lies on the border of the view it means it will stretch on infinitely out of the view
    private fun belongsToInfiniteGroup(coordinate: Coordinate, view: View) = coordinate.x == view.minX ||
            coordinate.y == view.minY ||
            coordinate.x == view.maxX ||
            coordinate.y == view.maxY

    private fun distanceToAllPoints(coordinate: Coordinate, points: List<Coordinate>): Int = points
        .map { coordinate.manhattanDistanceTo(it) }
        .sum()

    data class Coordinate(val x: Int, val y: Int) {

        fun manhattanDistanceTo(point: Coordinate) =
            abs(x - point.x) + abs(y - point.y)

        companion object {
            fun from(line: String) =
                line.split(", ")
                    .map { it.toInt() }
                    .let { Coordinate(it[0], it[1]) }

            fun from(pair: Pair<Int, Int>) =
                Coordinate(pair.first, pair.second)
        }
    }

    data class LocatedCoordinate(val coordinate: Coordinate, val nearest: Coordinate)

    data class View(val minX: Int, val minY: Int, val maxX: Int, val maxY: Int) {
        companion object {
            fun fromPoints(coordinates: List<Coordinate>): View {
                val minX = coordinates.minBy { it.x }!!.x
                val minY = coordinates.minBy { it.y }!!.y
                val maxX = coordinates.maxBy { it.x }!!.x
                val maxY = coordinates.maxBy { it.y }!!.y

                return View(minX, minY, maxX, maxY)
            }
        }
    }
}
