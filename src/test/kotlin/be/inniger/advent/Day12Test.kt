package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    private val input = readInputFile("12")
    private val sampleInput = listOf(
        "initial state: #..#.#..##......###...###",
        "",
        "...## => #",
        "..#.. => #",
        ".#... => #",
        ".#.#. => #",
        ".#.## => #",
        ".##.. => #",
        ".#### => #",
        "#.#.# => #",
        "#.### => #",
        "##.#. => #",
        "##.## => #",
        "###.. => #",
        "###.# => #",
        "####. => #"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(325, Day12.solveFirst(sampleInput))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(3_230, Day12.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(4_400_000_000_304, Day12.solveSecond(input))
    }
}
