package be.inniger.advent

import org.junit.Test
import kotlin.test.assertEquals

class Day01Test {

    private val problem: Day01 = Day01()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(3, problem.solveFirst(listOf(+1, -2, +3, +1)))

        assertEquals(3, problem.solveFirst(listOf(+1, +1, +1)))

        assertEquals(0, problem.solveFirst(listOf(+1, +1, -2)))

        assertEquals(-6, problem.solveFirst(listOf(-1, -2, -3)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(518, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(2, problem.solveSecond(listOf(+1, -2, +3, +1)))

        assertEquals(0, problem.solveSecond(listOf(+1, -1)))

        assertEquals(10, problem.solveSecond(listOf(+3, +3, +4, -2, -4)))

        assertEquals(5, problem.solveSecond(listOf(-6, +3, +8, +5, -6)))

        assertEquals(14, problem.solveSecond(listOf(+7, +7, -2, -7, -4)))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(72889, problem.solveSecond(input))
    }

    private val input = Thread.currentThread().contextClassLoader.getResourceAsStream("inputs/01.txt")
        .reader()
        .readLines()
        .map(String::toInt)
}
