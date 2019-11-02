package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day03Test {

    private val problem = Day03()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(0, problem.solveFirst(listOf("#123 @ 3,2: 5x4")))

        assertEquals(4, problem.solveFirst(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(120419, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("3", problem.solveSecond(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals("445", problem.solveSecond(input))
    }

    private val input = readInputFile("03")
}
