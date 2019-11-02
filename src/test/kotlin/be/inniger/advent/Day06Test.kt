package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day06Test {

    companion object {
        const val SAMPLE_UPPER_DISTANCE = 32
        const val PROBLEM_UPPER_DISTANCE = 10_000
    }

    private val problem = Day06()
    private val input = readInputFile("06")
    private val sampleCoordinates = listOf(
        "1, 1",
        "1, 6",
        "8, 3",
        "3, 4",
        "5, 5",
        "8, 9"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(17, problem.solveFirst(sampleCoordinates))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(4475, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(16, problem.solveSecond(sampleCoordinates, SAMPLE_UPPER_DISTANCE))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(35237, problem.solveSecond(input, PROBLEM_UPPER_DISTANCE))
    }
}
