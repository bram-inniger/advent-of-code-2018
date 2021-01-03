package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    private val input = readInputFile("04")
    private val records = listOf(
        "[1518-11-01 00:00] Guard #10 begins shift",
        "[1518-11-01 00:05] falls asleep",
        "[1518-11-01 00:25] wakes up",
        "[1518-11-01 00:30] falls asleep",
        "[1518-11-01 00:55] wakes up",
        "[1518-11-01 23:58] Guard #99 begins shift",
        "[1518-11-02 00:40] falls asleep",
        "[1518-11-02 00:50] wakes up",
        "[1518-11-03 00:05] Guard #10 begins shift",
        "[1518-11-03 00:24] falls asleep",
        "[1518-11-03 00:29] wakes up",
        "[1518-11-04 00:02] Guard #99 begins shift",
        "[1518-11-04 00:36] falls asleep",
        "[1518-11-04 00:46] wakes up",
        "[1518-11-05 00:03] Guard #99 begins shift",
        "[1518-11-05 00:45] falls asleep",
        "[1518-11-05 00:55] wakes up"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(240, Day04.solveFirst(records))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(101_262, Day04.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(4_455, Day04.solveSecond(records))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(71_976, Day04.solveSecond(input))
    }
}
