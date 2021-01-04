package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    private val input = readInputFile("14")[0].toInt()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("5158916779", Day14.solveFirst(9))
        assertEquals("0124515891", Day14.solveFirst(5))
        assertEquals("9251071085", Day14.solveFirst(18))
        assertEquals("5941429882", Day14.solveFirst(2_018))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("7116398711", Day14.solveFirst(input))
    }
}
