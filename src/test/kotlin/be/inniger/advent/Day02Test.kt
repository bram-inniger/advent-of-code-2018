package be.inniger.advent

import org.junit.Test
import kotlin.test.assertEquals

class Day02Test {

    private val problem: Day02 = Day02()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(0, problem.solveFirst(listOf("abcdef")))

        assertEquals(1, problem.solveFirst(listOf("bababc")))

        assertEquals(0, problem.solveFirst(listOf("abbcde")))

        assertEquals(0, problem.solveFirst(listOf("abcccd")))

        assertEquals(0, problem.solveFirst(listOf("aabcdd")))

        assertEquals(0, problem.solveFirst(listOf("abcdee")))

        assertEquals(0, problem.solveFirst(listOf("ababab")))

        assertEquals(
            12,
            problem.solveFirst(listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"))
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(6972, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("fgij", problem.solveSecond(listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals("aixwcbzrmdvpsjfgllthdyoqe", problem.solveSecond(input))
    }

    private val input = Thread.currentThread().contextClassLoader.getResourceAsStream("inputs/02.txt")
        .reader()
        .readLines()
}
