package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    private val input = readInputFile("07")
    private val requirements = listOf(
        "Step C must be finished before step A can begin.",
        "Step C must be finished before step F can begin.",
        "Step A must be finished before step B can begin.",
        "Step A must be finished before step D can begin.",
        "Step B must be finished before step E can begin.",
        "Step D must be finished before step E can begin.",
        "Step F must be finished before step E can begin."
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("CABDFE", Day07.solveFirst(requirements))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("CGKMUWXFAIHSYDNLJQTREOPZBV", Day07.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(15, Day07.solveSecond(requirements, 2, 0))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(1_046, Day07.solveSecond(input, 5, 60))
    }
}
