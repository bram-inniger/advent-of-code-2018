package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    private val input = readInputFile("01").map { it.toInt() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(3, Day01.solveFirst(listOf(+1, -2, +3, +1)))
        assertEquals(3, Day01.solveFirst(listOf(+1, +1, +1)))
        assertEquals(0, Day01.solveFirst(listOf(+1, +1, -2)))
        assertEquals(-6, Day01.solveFirst(listOf(-1, -2, -3)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(518, Day01.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(2, Day01.solveSecond(listOf(+1, -2, +3, +1)))
        assertEquals(0, Day01.solveSecond(listOf(+1, -1)))
        assertEquals(10, Day01.solveSecond(listOf(+3, +3, +4, -2, -4)))
        assertEquals(5, Day01.solveSecond(listOf(-6, +3, +8, +5, -6)))
        assertEquals(14, Day01.solveSecond(listOf(+7, +7, -2, -7, -4)))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(72_889, Day01.solveSecond(input))
    }
}
