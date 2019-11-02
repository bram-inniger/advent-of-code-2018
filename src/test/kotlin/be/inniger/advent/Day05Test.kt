package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {

    private val problem = Day05()
    private val input = readInputFile("05").joinToString("")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("dabCBAcaDA".length, problem.solveFirst("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(11298, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("daDA".length, problem.solveSecond("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(5148, problem.solveSecond(input))
    }
}
