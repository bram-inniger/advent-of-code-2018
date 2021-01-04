package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    private val input = readInputFile("14")[0]

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("5158916779", Day14.solveFirst(9))
        assertEquals("0124515891", Day14.solveFirst(5))
        assertEquals("9251071085", Day14.solveFirst(18))
        assertEquals("5941429882", Day14.solveFirst(2_018))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("7116398711", Day14.solveFirst(input.toInt()))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(9, Day14.solveSecond("51589"))
        assertEquals(5, Day14.solveSecond("01245"))
        assertEquals(18, Day14.solveSecond("92510"))
        assertEquals(2_018, Day14.solveSecond("59414"))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(20_316_365, Day14.solveSecond(input))
    }
}
