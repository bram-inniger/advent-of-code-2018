package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    private val input = readInputFile("08")[0].split(' ').map { it.toInt() }
    private val license = listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(138, Day08.solveFirst(license))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(36_566, Day08.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(66, Day08.solveSecond(license))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(30_548, Day08.solveSecond(input))
    }
}
