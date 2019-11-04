package be.inniger.advent

class Day09 {

    fun solveFirst(gameDescription: String): Int {
        val game = Game.from(gameDescription)
        val scores = IntArray(game.nrPlayers)
        var currentNode = Node(0)

        for (marble in 1..game.lastMarble) {
            val player = (marble - 1) % game.nrPlayers
            val turn = takeTurn(marble, currentNode)

            currentNode = turn.currentNode
            scores[player] += turn.points
        }

        return scores.max()!!
    }

    private fun takeTurn(marble: Int, currentNode: Node): Turn {
        return if (marble % 23 != 0) {
            addMarble(marble, currentNode)
        } else {
            scoreMarble(marble, currentNode)
        }
    }

    private fun addMarble(marble: Int, currentNode: Node): Turn {
        val prev = currentNode.next
        val next = prev.next

        // To be added node, pointing to the correct prev and next ones, putting itself in between
        val addedNode = Node(marble, prev, next)

        // Rewiring the old prev and next nodes to point to the inserted one
        prev.next = addedNode
        next.prev = addedNode

        return Turn(addedNode)
    }

    private fun scoreMarble(marble: Int, currentNode: Node): Turn {
        var node = currentNode
        var points = marble

        repeat(7) { node = node.prev } // 7 times to the left counter-clockwise
        points += node.marble

        // Mark the to-be-removed nodes prev and next
        val prev = node.prev
        val next = node.next

        // Wire these together, effectively removing the node in between
        prev.next = next
        next.prev = prev

        return Turn(next, points)
    }

    // Doubly linked node to easily shift left-right in a circular fashion
    private class Node {

        internal val marble: Int
        internal var prev: Node
        internal var next: Node

        internal constructor(marble: Int) {
            this.marble = marble
            this.prev = this
            this.next = this
        }

        internal constructor(marble: Int, prev: Node, next: Node) {
            this.marble = marble
            this.prev = prev
            this.next = next
        }
    }

    private data class Turn(internal val currentNode: Node, internal val points: Int = 0)

    private data class Game(internal val nrPlayers: Int, internal val lastMarble: Int) {
        companion object {
            //428 players; last marble is worth 70825 points
            private val regex = """^(\d+) players; last marble is worth (\d+) points$""".toRegex()

            internal fun from(gameDescription: String): Game {
                val (nrPlayers, lastMarble) = regex.find(gameDescription)!!.destructured

                return Game(nrPlayers.toInt(), lastMarble.toInt())
            }
        }
    }
}
