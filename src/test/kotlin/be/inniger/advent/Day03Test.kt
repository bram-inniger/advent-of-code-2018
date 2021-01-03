package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    private val input = readInputFile("03")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(0, Day03.solveFirst(listOf("#123 @ 3,2: 5x4")))
        assertEquals(4, Day03.solveFirst(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(120_419, Day03.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("3", Day03.solveSecond(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals("445", Day03.solveSecond(input))
    }
}
