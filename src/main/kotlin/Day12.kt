package be.inniger.advent

import java.util.SortedSet

class Day12 {

    companion object {
        private const val SMALL_NR_GENERATIONS = 20L
        private const val BIG_NR_GENERATIONS = 50_000_000_000L
    }

    fun solveFirst(input: List<String>) =
        solve(input, SMALL_NR_GENERATIONS)

    fun solveSecond(input: List<String>) =
        solve(input, BIG_NR_GENERATIONS)

    private fun solve(input: List<String>, nrGenerations: Long): Long {
        val initialPlants = parseInitialPlants(input[0])
        val patterns = input.drop(2)
            .map { Pattern.from(it) }
            .filter { it.plant }
        val repeatingPlants = findRepeatingPlants(initialPlants, patterns)

        return if (nrGenerations <= repeatingPlants.startingGeneration) { // Loop over all needed generations
            var plants = initialPlants

            repeat(nrGenerations.toInt()) {
                plants = findNextGeneration(plants, patterns)
            }

            plants.sum()
        } else { // Use the fact that the plants remain the same after a while but just shift all together
            val movementShift = (nrGenerations - repeatingPlants.startingGeneration) * repeatingPlants.movementDelta

            repeatingPlants.plants
                .map { it + movementShift }
                .sum()
        }
    }

    private fun parseInitialPlants(initialState: String): SortedSet<Long> {
        val regex = """^initial state: ([.#]+)$""".toRegex()
        val (plantsDescription) = regex.find(initialState)!!.destructured

        return plantsDescription.indices
            .filter { plantsDescription[it] == '#' }
            .map { it.toLong() }
            .toSortedSet()
    }

    private fun findRepeatingPlants(initialPlants: SortedSet<Long>, patterns: List<Pattern>): RepeatingPlants {
        var generation = 0L
        var plants = initialPlants

        while (true) {
            val nextPlants = findNextGeneration(plants, patterns)

            if (plantsMatch(plants, nextPlants)) {
                val movementDelta = nextPlants.min()!! - plants.min()!!

                return RepeatingPlants(plants, movementDelta, generation)
            }

            generation++
            plants = nextPlants
        }
    }

    private fun plantsMatch(prevGeneration: SortedSet<Long>, nextGeneration: SortedSet<Long>): Boolean {
        val shift = prevGeneration.min()!! - nextGeneration.min()!!

        return prevGeneration.size == nextGeneration.size &&
                prevGeneration.all { nextGeneration.contains(it - shift) }
    }

    private fun findNextGeneration(plants: SortedSet<Long>, patterns: List<Pattern>): SortedSet<Long> {
        val smallestPossiblePlant = plants.min()!! - 2
        val biggestPossiblePlant = plants.max()!! + 2

        return (smallestPossiblePlant..biggestPossiblePlant)
            .filter { plant ->
                patterns.any { pattern -> matchesPattern(plant, plants, pattern) }
            }
            .toSortedSet()
    }

    private fun matchesPattern(plant: Long, plants: SortedSet<Long>, pattern: Pattern) =
        pattern.combination.indices
            .all { pattern.combination[it] == plants.contains(plant - 2 + it) }

    private data class Pattern(internal val combination: List<Boolean>, internal val plant: Boolean) {
        companion object {
            private val regex = """^([.#]{5}) => ([.#])$""".toRegex()

            internal fun from(pointDescription: String): Pattern {
                val (combination, plant) = regex.find(pointDescription)!!.destructured

                return Pattern(combination.map { parseChar(it) }, parseChar(plant.single()))
            }

            internal fun parseChar(char: Char) = when (char) {
                '#' -> true
                '.' -> false
                else -> throw IllegalArgumentException()
            }
        }
    }

    private data class RepeatingPlants(
        internal val plants: SortedSet<Long>,
        internal val movementDelta: Long,
        internal val startingGeneration: Long
    )
}
