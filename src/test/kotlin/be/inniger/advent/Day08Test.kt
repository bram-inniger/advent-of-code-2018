package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day08Test {

    private val problem = Day08()
    private val input = readInputFile("08")[0].split(' ').map { it.toInt() }
    private val license = listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(138, problem.solveFirst(license))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(36566, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(66, problem.solveSecond(license))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(30548, problem.solveSecond(input))
    }
}
