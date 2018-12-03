package be.inniger.advent

class Day01 {

    fun solveFirst(changes: List<String>) = changes.map(String::toInt).sum()

    fun solveSecond(changes: List<String>): Int {
        return solveSecondRec(changes.map(String::toInt), 0, mutableSetOf(), 0)
    }

    // frequenciesSeen is mutable for performance reasons
    private tailrec fun solveSecondRec(
        changes: List<Int>, frequency: Int, frequenciesSeen: MutableSet<Int>, counter: Int
    ): Int {
        return if (frequenciesSeen.contains(frequency)) {
            frequency
        } else {
            frequenciesSeen.add(frequency)
            solveSecondRec(
                changes, frequency + changes[counter % changes.size], frequenciesSeen, counter + 1
            )
        }
    }
}
