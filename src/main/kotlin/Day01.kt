package be.inniger.advent

import org.pcollections.HashTreePSet
import org.pcollections.PSet

class Day01 {

    fun solveFirst(changes: List<String>) = changes.map(String::toInt).sum()

    fun solveSecond(changes: List<String>): Int {
        return solveSecondRec(changes.map(String::toInt), 0, HashTreePSet.empty(), 0)
    }

    private tailrec fun solveSecondRec(
        changes: List<Int>, frequency: Int, frequenciesSeen: PSet<Int>, counter: Int
    ): Int {
        return if (frequenciesSeen.contains(frequency)) {
            frequency
        } else {
            solveSecondRec(
                changes, frequency + changes[counter % changes.size], frequenciesSeen.plus(frequency), counter + 1
            )
        }
    }
}
