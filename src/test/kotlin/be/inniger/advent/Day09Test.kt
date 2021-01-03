package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    private val input = readInputFile("09")[0]

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(32, Day09.solveFirst("9 players; last marble is worth 25 points"))
        assertEquals(8_317, Day09.solveFirst("10 players; last marble is worth 1618 points"))
        assertEquals(146_373, Day09.solveFirst("13 players; last marble is worth 7999 points"))
        assertEquals(2_764, Day09.solveFirst("17 players; last marble is worth 1104 points"))
        assertEquals(54_718, Day09.solveFirst("21 players; last marble is worth 6111 points"))
        assertEquals(37_305, Day09.solveFirst("30 players; last marble is worth 5807 points"))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(398_502, Day09.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(3_352_920_421, Day09.solveSecond(input))
    }
}
