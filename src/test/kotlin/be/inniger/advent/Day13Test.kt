package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    private val input = readInputFile("13")
    private val tracks = listOf(
        "/->-\\",
        "|   |  /----\\",
        "| /-+--+-\\  |",
        "| | |  | v  |",
        "\\-+-/  \\-+--/",
        "  \\------/"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("7,3", Day13.solveFirst(tracks))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("32,99", Day13.solveFirst(input))
    }
}
