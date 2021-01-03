package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    private val input = readInputFile("13")

    @Test
    fun validateFirstSampleInputs() {
        val tracks = listOf(
            "/->-\\",
            "|   |  /----\\",
            "| /-+--+-\\  |",
            "| | |  | v  |",
            "\\-+-/  \\-+--/",
            "  \\------/"
        )

        assertEquals("7,3", Day13.solveFirst(tracks))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("32,99", Day13.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        val tracks = listOf(
            "/>-<\\",
            "|   |",
            "| /<+-\\",
            "| | | v",
            "\\>+</ |",
            "  |   ^",
            "  \\<->/"
        )

        assertEquals("6,4", Day13.solveSecond(tracks))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals("56,31", Day13.solveSecond(input))
    }
}
