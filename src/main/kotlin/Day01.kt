package be.inniger.advent

import org.pcollections.HashTreePSet
import org.pcollections.PSet

class Day01 {

    fun solveFirst(changes: List<Int>) = changes.sum()

    tailrec fun solveSecond(changes: List<Int>, frequency: Int = 0, reached: PSet<Int> = HashTreePSet.empty()): Int =
        if (reached.contains(frequency)) frequency
        else solveSecond(changes, frequency + changes[reached.size % changes.size], reached.plus(frequency))
}
