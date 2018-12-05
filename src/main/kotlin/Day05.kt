package be.inniger.advent

class Day05 {

    // TODO rewrite without mutable state
    fun solveFirst(polymerStr: String): Int {
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
