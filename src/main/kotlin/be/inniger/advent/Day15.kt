package be.inniger.advent

object Day15 {

    fun solveFirst(areaRaw: List<String>): Int {
        val area = Area.of(areaRaw)
        val creatures = readCreatures(areaRaw)
        var nrElves = creatures.values.filter { it.kind == Creature.Kind.E }.count()
        var nrGoblins = creatures.values.filter { it.kind == Creature.Kind.G }.count()
        var round = 0

        while (true) {
            val moveOrder = creatures.entries
                .sortedWith(compareBy({ it.key.y }, { it.key.x }))

            for (creature in moveOrder) {
                // Game ends when no targets are available
                if (nrElves == 0 || nrGoblins == 0) {
//                    println()
//                    println("After $round rounds:")
//                    println(gridToAscii(area, creatures))
                    return round * creatures.values.sumBy { it.hitPoints }
                }

                if (!creatures.containsKey(creature.key)) continue // Creature already killed, skip turn

                // move
                val oldPosition = creature.key
                val newPosition = selectNewPosition(oldPosition, area, creatures)
                if (newPosition != null) {
                    creatures.remove(creature.key)
                    creatures[newPosition] = creature.value
                }
                val newCreature = (newPosition ?: oldPosition) to creature.value

                // attack
                val target = selectTarget(newCreature, area, creatures)
                if (target != null) {
                    val newTarget = Creature(
                        target.second.kind,
                        target.second.hitPoints - creature.value.attackPower
                    )

                    if (newTarget.hitPoints <= 0) { // Kill
                        creatures.remove(target.first)
                        when (target.second.kind) {
                            Creature.Kind.E -> nrElves--
                            Creature.Kind.G -> nrGoblins--
                        }
                    } else { // Still alive
                        creatures[target.first] = newTarget
                    }
                }
            }

            round++

//            println()
//            println("After $round rounds:")
//            println(gridToAscii(area, creatures))
        }
    }

    private fun readCreatures(area: List<String>) =
        area.flatMapIndexed { y, areaLine ->
            areaLine.mapIndexed { x, c ->
                when (c) {
                    'E' -> Coordinate.of(x, y) to Creature.elf()
                    'G' -> Coordinate.of(x, y) to Creature.goblin()
                    '.', '#' -> null
                    else -> error("Invalid input: $c at position $x, $y")
                }
            }
        }
            .filterNotNull()
            .toMap()
            .toMutableMap()

    private fun selectNewPosition(
        oldPosition: Coordinate,
        area: Area,
        creatures: Map<Coordinate, Creature>
    ): Coordinate? {
        // FIXME -- should be able to remove this whole if-statement
        if (selectTarget(oldPosition to creatures[oldPosition]!!, area, creatures) != null) {
            return null
        }

        val attacker = creatures[oldPosition]!!
        var depth = 1
        var found = false
        val visited = mutableSetOf<Coordinate>()
        val rootNode = Node(oldPosition, null, depth)
        val queue = ArrayDeque(listOf(rootNode))
        val targets = mutableListOf<Node>()

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            visited.add(node.position)

            // set current depth to queue.head().depth
            if (!found) {
                depth = node.depth
            } else if (node.depth > depth) {
                break
            }

            // if enemy found -> mark found == true, save enemy in list
            val defenderKind = creatures[node.position]?.kind
            if (defenderKind?.isEnemy(attacker.kind) == true) {
                found = true
                targets.add(node)
            }

            // else generate new nodes and put them in the queue (unless found == true)
            if (!found) {
                readingOrderNeighbours(node.position)
                    .asSequence()
                    .filter { it.x >= 0 && it.x < area.width && it.y >= 0 && it.y < area.height }
                    .filter { area.grid.contains(it) }
                    .filter { creatures[it]?.kind != attacker.kind }
                    .filter { !visited.contains(it) }
                    .map { Node(it, node, node.depth + 1) }
                    .forEach { queue.addLast(it) }
            }
        }

        var target = targets
            .sortedWith(compareBy({ it.position.y }, { it.position.x }))
            .firstOrNull()
            ?: return null

        while (target.parent!!.position != oldPosition) {
            target = target.parent!!
        }

        return target.position
    }

    private fun selectTarget(attacker: Pair<Coordinate, Creature>, area: Area, creatures: Map<Coordinate, Creature>) =
        readingOrderNeighbours(attacker.first)
            .asSequence()
            .filter { it.x >= 0 && it.x < area.width && it.y >= 0 && it.y < area.height }
            .map { it to creatures[it] }
            .filter { it.second != null }
            .map { it.first to it.second!! }
            .filter { defender -> defender.second.kind.isEnemy(attacker.second.kind) }
            .sortedWith(
                compareBy({ it.second.hitPoints }, { it.first.y }, { it.first.x })
            )
            .firstOrNull()

    private fun readingOrderNeighbours(position: Coordinate) =
        listOf(
            Coordinate.of(position.x, position.y - 1), // Up
            Coordinate.of(position.x - 1, position.y), // Left
            Coordinate.of(position.x + 1, position.y), // Right
            Coordinate.of(position.x, position.y + 1)  // Down
        )

    private fun gridToAscii(area: Area, creatures: Map<Coordinate, Creature>) =
        (0 until area.height)
            .joinToString("\n") { y ->
                (0 until area.width)
                    .map { x -> Coordinate.of(x, y) }
                    .map {
                        when {
                            creatures.containsKey(it) -> creatures[it]!!.ascii()
                            area.grid.contains(it) -> '.'
                            else -> '#'
                        }
                    }
                    .joinToString("") + "  " + creaturesToAscii(y, creatures)
            }

    private fun creaturesToAscii(row: Int, creatures: Map<Coordinate, Creature>) =
        creatures.entries
            .filter { it.key.y == row }
            .sortedBy { it.key.x }
            .map { it.value }
            .joinToString(", ") { "${it.kind.name}(${it.hitPoints})" }

    private data class Area(val grid: Set<Coordinate>, val width: Int, val height: Int) {

        companion object {
            fun of(area: List<String>) = Area(
                area.flatMapIndexed { y: Int, areaLine: String ->
                    areaLine.mapIndexed { x, c ->
                        when (c) {
                            '.', 'E', 'G' -> Coordinate.of(x, y)
                            '#' -> null
                            else -> error("Invalid input: $c at position $x, $y")
                        }
                    }
                }
                    .filterNotNull()
                    .toSet(),
                area.size,
                area[0].length
            )
        }
    }

    private data class Creature(val kind: Kind, val hitPoints: Int = 200, val attackPower: Int = 3) {

        enum class Kind {
            E,
            G;

            fun isEnemy(other: Kind) = when (this) {
                E -> other == G
                G -> other == E
            }
        }

        companion object {
            fun elf() = Creature(Kind.E)
            fun goblin() = Creature(Kind.G)
        }

        fun ascii() = kind.name.single()
    }

    private data class Coordinate(val x: Int, val y: Int) {
        companion object {
            private const val MAX_GRID = 50
            private val cache = (0..MAX_GRID).map { y -> (0..MAX_GRID).map { x -> Coordinate(x, y) } }

            fun of(x: Int, y: Int) = cache[y][x]
        }
    }

    private data class Node(val position: Coordinate, val parent: Node?, val depth: Int)
}
