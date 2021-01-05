package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    private val input = readInputFile("16").joinToString("\n")

    @Test
    fun validateFirstSampleInputs() {
        val samples = """
            Before: [3, 2, 1, 1]
            9 2 1 2
            After:  [3, 2, 2, 1]
        """.trimIndent()

        assertEquals(1, Day16.solveFirst(samples))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(544, Day16.solveFirst(input))
    }
}
