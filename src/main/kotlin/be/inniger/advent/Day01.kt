package be.inniger.advent

object Day01 {

    fun solveFirst(changes: List<Int>) = changes.sum()

    fun solveSecond(changes: List<Int>): Int {
        val visited = mutableSetOf<Int>()

        tailrec fun findFrequency(frequency: Int): Int =
            if (visited.contains(frequency)) frequency
            else {
                visited.add(frequency)
                findFrequency(frequency + changes[(visited.size - 1) % changes.size])
            }

        return findFrequency(0)
    }
}
