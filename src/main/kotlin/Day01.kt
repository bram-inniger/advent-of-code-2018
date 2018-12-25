package be.inniger.advent

import io.vavr.collection.HashSet
import io.vavr.collection.Set

class Day01 {

    fun solveFirst(changes: List<Int>) = changes.sum()

    tailrec fun solveSecond(changes: List<Int>, frequency: Int = 0, reached: Set<Int> = HashSet.empty()): Int =
        if (reached.contains(frequency)) frequency
        else solveSecond(changes, frequency + changes[reached.size() % changes.size], reached.add(frequency))
}
