package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day06Test {

    private val problem = Day06()

    @Test
    fun validateFirstSampleInputs() {
        val coordinates = listOf(
            "1, 1",
            "1, 6",
            "8, 3",
            "3, 4",
            "5, 5",
            "8, 9"
        )

        assertEquals(17, problem.solveFirst(coordinates))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(4475, problem.solveFirst(input))
    }

    private val input = readInputFile("06")
}
