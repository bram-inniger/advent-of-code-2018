package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

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
        assertEquals(17, Day06.solveFirst(sampleCoordinates))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(4_475, Day06.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(16, Day06.solveSecond(sampleCoordinates, 32))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(35_237, Day06.solveSecond(input, 10_000))
    }
}
