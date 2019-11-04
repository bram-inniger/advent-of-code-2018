package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day09Test {

    private val problem = Day09()
    private val input = readInputFile("09")[0]

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(32, problem.solveFirst("9 players; last marble is worth 25 points"))

        assertEquals(8317, problem.solveFirst("10 players; last marble is worth 1618 points"))

        assertEquals(146373, problem.solveFirst("13 players; last marble is worth 7999 points"))

        assertEquals(2764, problem.solveFirst("17 players; last marble is worth 1104 points"))

        assertEquals(54718, problem.solveFirst("21 players; last marble is worth 6111 points"))

        assertEquals(37305, problem.solveFirst("30 players; last marble is worth 5807 points"))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(398502, problem.solveFirst(input))
    }
}
