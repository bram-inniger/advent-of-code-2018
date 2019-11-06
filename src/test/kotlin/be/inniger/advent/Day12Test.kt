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
        assertEquals(325, problem.solveFirst(sampleInput))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(3230, problem.solveFirst(input))
    }
}
