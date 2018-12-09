package be.inniger.advent

class Day05 {

    fun solveFirst(polymer: String) = calculateCollapsedPolymerLength(polymer)

    fun solveSecond(polymer: String) = ('a'..'z')
        .map { polymer.replace("""[$it${pairs[it]}]""".toRegex(), "") }
        .map { calculateCollapsedPolymerLength(it) }
        .min()!!

    private fun calculateCollapsedPolymerLength(polymerStr: String): Int {
        val polymer = StringBuilder(polymerStr)
        var done = false

        while (!done) {
            done = true
            var i = polymer.lastIndex

            while (i > 0) {
                if (pairs[polymer[i]] == polymer[i - 1]) {
                    polymer.deleteCharAt(i)
                    i--
                    polymer.deleteCharAt(i)
                    done = false
                }

                i--
            }
        }

        return polymer.length
    }

    private val pairs = (('a'..'z') + ('A'..'Z'))
        .zip(('A'..'Z') + ('a'..'z'))
        .associate { it.first to it.second }
}
