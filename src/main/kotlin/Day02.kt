package be.inniger.advent

class Day02 {

    fun solveFirst(boxIds: List<String>) =
        nrBoxIdsContainingLettersTimes(boxIds, 2) * nrBoxIdsContainingLettersTimes(boxIds, 3)

    private fun nrBoxIdsContainingLettersTimes(boxIds: List<String>, nrTimes: Int) =
        boxIds.filter { label ->
            label.groupBy { it }
                .values
                .map(List<Char>::size)
                .any { it == nrTimes }
        }
            .count()
}
