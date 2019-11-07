package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {

    private val problem = Day12()
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
        assertEquals(325L, problem.solveFirst(sampleInput))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(3230L, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(4_400_000_000_304L, problem.solveSecond(input))
    }
}
