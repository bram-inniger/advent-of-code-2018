package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    private val input = readInputFile("11")[0].toInt()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("33,45", Day11.solveFirst(18))
        assertEquals("21,61", Day11.solveFirst(42))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("235,16", Day11.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
//        assertEquals("90,269,16", problem.solveSecond(18)) // FIXME too slow
//        assertEquals("232,251,12", problem.solveSecond(42)) // FIXME too slow
    }

    @Test
    fun validateSecondSolution() {
//        assertEquals("236,227,14", problem.solveSecond(input)) // FIXME too slow
    }
}
