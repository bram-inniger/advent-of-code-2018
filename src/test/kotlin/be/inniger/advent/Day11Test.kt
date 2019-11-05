package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {

    private val problem = Day11()
    private val input = readInputFile("11")[0].toInt()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("33,45", problem.solveFirst(18))

        assertEquals("21,61", problem.solveFirst(42))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("235,16", problem.solveFirst(input))
    }
}
