package be.inniger.advent

class Day02 {

    fun solveFirst(boxIds: List<String>) =
        nrBoxIdsContainingLettersTimes(boxIds, 2) * nrBoxIdsContainingLettersTimes(boxIds, 3)

    private fun nrBoxIdsContainingLettersTimes(boxIds: List<String>, nrTimes: Int) =
        boxIds.map { boxId ->
            boxId.groupBy { it }.values.map { it.size }
        }
            .count { it.contains(nrTimes) }
}
