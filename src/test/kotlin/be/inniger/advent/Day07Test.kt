package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day07Test {

    companion object {
        const val SAMPLE_NR_WORKERS = 2
        const val SAMPLE_TIME_ADDITION = 0
        const val PROBLEM_NR_WORKERS = 5
        const val PROBLEM_TIME_ADDITION = 60
    }

    private val problem = Day07()
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
        assertEquals("CABDFE", problem.solveFirst(requirements))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals("CGKMUWXFAIHSYDNLJQTREOPZBV", problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(15, problem.solveSecond(requirements, SAMPLE_NR_WORKERS, SAMPLE_TIME_ADDITION))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(1046, problem.solveSecond(input, PROBLEM_NR_WORKERS, PROBLEM_TIME_ADDITION))
    }
}
