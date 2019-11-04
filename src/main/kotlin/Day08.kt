package be.inniger.advent

class Day08 {

    fun solveFirst(license: List<Int>) =
        processNode(license, 0).metadataSum

    private fun processNode(representation: List<Int>, beginPosition: Int): Node {
        var metadataSum = 0
        var position = beginPosition
        val nrNodes = representation[position++]
        val nrMetadataRecords = representation[position++]

        repeat(nrNodes) {
            val node = processNode(representation, position)
            metadataSum += node.metadataSum
            position = node.endPosition + 1
        }

        for (i in 0 until nrMetadataRecords) {
            metadataSum += representation[position + i]
        }

        return Node(metadataSum, position + nrMetadataRecords - 1)
    }

    private data class Node(val metadataSum: Int, val endPosition: Int)
}
