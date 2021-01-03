package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    private val input = readInputFile("02")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(0, Day02.solveFirst(listOf("abcdef")))
        assertEquals(1, Day02.solveFirst(listOf("bababc")))
        assertEquals(0, Day02.solveFirst(listOf("abbcde")))
        assertEquals(0, Day02.solveFirst(listOf("abcccd")))
        assertEquals(0, Day02.solveFirst(listOf("aabcdd")))
        assertEquals(0, Day02.solveFirst(listOf("abcdee")))
        assertEquals(0, Day02.solveFirst(listOf("ababab")))
        assertEquals(
            12,
            Day02.solveFirst(listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"))
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(6_972, Day02.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("fgij", Day02.solveSecond(listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals("aixwcbzrmdvpsjfgllthdyoqe", Day02.solveSecond(input))
    }
}
