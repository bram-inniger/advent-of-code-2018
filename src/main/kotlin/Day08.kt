package be.inniger.advent

class Day08 {

    fun solveFirst(license: List<Int>) =
        processNode(representation = license, simpleProcessing = true).value

    fun solveSecond(license: List<Int>) =
        processNode(representation = license, simpleProcessing = false).value

    private fun processNode(representation: List<Int>, beginPosition: Int = 0, simpleProcessing: Boolean): Node {
        var value = 0
        var position = beginPosition
        val nrNodes = representation[position++]
        val nrMetadataRecords = representation[position++]
        val children = mutableListOf<Node>()

        repeat(nrNodes) {
            val node = processNode(representation, position, simpleProcessing)
            children.add(node)
            position = node.endPosition + 1
        }

        if (simpleProcessing || nrNodes == 0) { // Calculate sum of metadata records
            value += children.map { it.value }.sum()

            for (i in 0 until nrMetadataRecords) {
                value += representation[position + i]
            }
        } else { // Calculate using the metadata contents as indexed for child nodes
            for (i in 0 until nrMetadataRecords) {
                val index = representation[position + i]
                val nodeValue = if (index <= children.size) children[index - 1].value else 0

                value += nodeValue
            }
        }

        return Node(value, position + nrMetadataRecords - 1)
    }

    private data class Node(val value: Int, val endPosition: Int)
}
