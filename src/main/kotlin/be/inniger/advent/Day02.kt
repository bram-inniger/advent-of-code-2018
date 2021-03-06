package be.inniger.advent

object Day02 {

    fun solveFirst(boxIds: List<String>) =
        nrBoxIdsContainingLettersTimes(boxIds, 2) * nrBoxIdsContainingLettersTimes(boxIds, 3)

    fun solveSecond(boxIds: List<String>): String {
        val wordLength = boxIds.first().length // Inputs all have same length

        return cartesianSelfProduct(boxIds)
            .map { getCommonLetters(it) }
            .filter { it.length == wordLength - 1 }
            .distinct()
            .single()
    }

    private fun nrBoxIdsContainingLettersTimes(boxIds: List<String>, nrTimes: Int) =
        boxIds.map { boxId -> boxId.groupingBy { it }.eachCount() }
            .map { it.values }
            .count { it.contains(nrTimes) }

    private fun cartesianSelfProduct(boxIds: List<String>) =
        boxIds.flatMap { left -> boxIds.map { right -> left to right } }

    private fun getCommonLetters(words: Pair<String, String>) =
        words.first
            .zip(words.second)
            .filter { it.first == it.second }
            .map { it.first }
            .joinToString("")
}
