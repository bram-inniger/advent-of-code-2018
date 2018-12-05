package be.inniger.advent

import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {

    private val problem: Day05 = Day05()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("dabCBAcaDA".length, problem.solveFirst("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(11298, problem.solveFirst(input))
    }

    private val input = Thread.currentThread().contextClassLoader.getResourceAsStream("inputs/05.txt")
        .reader()
        .readLines()
        .joinToString("")
}
