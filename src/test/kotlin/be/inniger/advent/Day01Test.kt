package be.inniger.advent

import org.junit.Test
import kotlin.test.assertEquals

class Day01Test {

    private val problem: Day01 = Day01()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(3, problem.solveFirst(listOf("+1", "-2", "+3", "+1")))

        assertEquals(3, problem.solveFirst(listOf("+1", "+1", "+1")))

        assertEquals(0, problem.solveFirst(listOf("+1", "+1", "-2")))

        assertEquals(-6, problem.solveFirst(listOf("-1", "-2", "-3")))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(0, problem.solveFirst(input))
    }

    private val input = Thread.currentThread().contextClassLoader.getResourceAsStream("inputs/01.txt")
        .reader()
        .readLines()
}
