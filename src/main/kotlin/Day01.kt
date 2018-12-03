package be.inniger.advent

import org.pcollections.HashTreePSet
import org.pcollections.PSet

class Day01 {

    fun solveFirst(changes: List<String>) = changes.map(String::toInt).sum()

    fun solveSecond(changes: List<String>): Int {
        return findRepeatedFrequency(changes.map(String::toInt), 0, HashTreePSet.empty())
    }

    private tailrec fun findRepeatedFrequency(changes: List<Int>, frequency: Int, reached: PSet<Int>): Int =
        if (reached.contains(frequency)) {
            frequency
        } else {
            findRepeatedFrequency(changes, frequency + changes[reached.size % changes.size], reached.plus(frequency))
        }
}
