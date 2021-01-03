package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    private val input = readInputFile("05").joinToString("")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("dabCBAcaDA".length, Day05.solveFirst("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(11_298, Day05.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("daDA".length, Day05.solveSecond("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(5_148, Day05.solveSecond(input))
    }
}
