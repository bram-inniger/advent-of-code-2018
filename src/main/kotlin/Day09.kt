package be.inniger.advent

class Day09 {

    companion object {
        private const val STARTING_MARBLE = 0L
        private const val SCORING_COEFFICIENT = 23
        private const val STANDARD_GAME_FACTOR = 1
        private const val LONG_GAME_FACTOR = 100
    }

    fun solveFirst(gameDescription: String) =
        play(Game.from(gameDescription, STANDARD_GAME_FACTOR))

    fun solveSecond(gameDescription: String) =
        play(Game.from(gameDescription, LONG_GAME_FACTOR))

    private fun play(game: Game): Long {
        val scores = LongArray(game.nrPlayers)
        var currentNode = Node(STARTING_MARBLE)

        for (marble in 1..game.lastMarble) {
            val player = ((marble - 1) % game.nrPlayers).toInt()
            val turn = takeTurn(marble, currentNode)

            currentNode = turn.currentNode
            scores[player] = scores[player] + turn.points
        }

        return scores.max()!!
    }

    private fun takeTurn(marble: Long, currentNode: Node): Turn {
        return if (marble % SCORING_COEFFICIENT != 0L) {
            addMarble(marble, currentNode)
        } else {
            scoreMarble(marble, currentNode)
        }
    }

    private fun addMarble(marble: Long, currentNode: Node): Turn {
        val prev = currentNode.next
        val next = prev.next

        // To be added node, pointing to the correct prev and next ones, putting itself in between
        val addedNode = Node(marble, prev, next)

        // Rewiring the old prev and next nodes to point to the inserted one
        prev.next = addedNode
        next.prev = addedNode

        return Turn(addedNode)
    }

    private fun scoreMarble(marble: Long, currentNode: Node): Turn {
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
        internal val marble: Long
        internal var prev: Node
        internal var next: Node

        internal constructor(marble: Long) {
            this.marble = marble
            this.prev = this
            this.next = this
        }

        internal constructor(marble: Long, prev: Node, next: Node) {
            this.marble = marble
            this.prev = prev
            this.next = next
        }
    }

    private data class Turn(internal val currentNode: Node, internal val points: Long = 0L)

    private data class Game(internal val nrPlayers: Int, internal val lastMarble: Long) {
        companion object {
            private val regex = """^(\d+) players; last marble is worth (\d+) points$""".toRegex()

            internal fun from(gameDescription: String, gameFactor: Int): Game {
                val (nrPlayers, lastMarble) = regex.find(gameDescription)!!.destructured

                return Game(nrPlayers.toInt(), lastMarble.toLong() * gameFactor)
            }
        }
    }
}
