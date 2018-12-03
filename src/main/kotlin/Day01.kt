package be.inniger.advent

class Day01 {

    fun solveFirst(frequencies: List<String>) = frequencies.map(String::toInt).sum()

    fun solveSecond(strFrequencies: List<String>): Int {
        val frequencies = strFrequencies.map(String::toInt)
        val encounteredFrequencies = HashSet<Int>()

        var currentFrequency = 0
        var counter = 0

        do {
            encounteredFrequencies.add(currentFrequency)
            currentFrequency += frequencies[counter++ % frequencies.size]
        } while (!encounteredFrequencies.contains(currentFrequency))

        return currentFrequency
    }
}
