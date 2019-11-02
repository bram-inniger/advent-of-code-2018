package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day07Test {

    private val problem = Day07()

    @Test
    fun validateFirstSampleInputs() {
        val requirements = listOf(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
        )

        assertEquals("CABDFE", problem.solveFirst(requirements))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("CGKMUWXFAIHSYDNLJQTREOPZBV", problem.solveFirst(input))
    }

    private val input = readInputFile("07")
}
