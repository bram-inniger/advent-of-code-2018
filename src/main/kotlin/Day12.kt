package be.inniger.advent

import java.util.SortedSet

class Day12 {

    companion object {
        private const val NR_GENERATIONS = 20
    }

    fun solveFirst(input: List<String>): Int {
        var plants = parseInitialPlants(input[0])
        val patterns = (2 until input.size)
            .map { input[it] }
            .map { Pattern.from(it) }
            .filter { it.plant }

        for (i in 0 until NR_GENERATIONS) {
            plants = findNextGeneration(plants, patterns)
        }

        return plants.sum()
    }

    private fun parseInitialPlants(initialState: String): SortedSet<Int> {
        val regex = """^initial state: ([.#]+)$""".toRegex()
        val (plantsDescription) = regex.find(initialState)!!.destructured

        return plantsDescription.indices
            .filter { plantsDescription[it] == '#' }
            .toSortedSet()
    }

    private fun findNextGeneration(plants: SortedSet<Int>, patterns: List<Pattern>): SortedSet<Int> {
        val smallestPossiblePlant = plants.min()!! - 2
        val biggestPossiblePlant = plants.max()!! + 2

        return (smallestPossiblePlant..biggestPossiblePlant)
            .filter { plant ->
                patterns.any { pattern -> matchesPattern(plant, plants, pattern) }
            }
            .toSortedSet()
    }

    private fun matchesPattern(plant: Int, plants: SortedSet<Int>, pattern: Pattern) =
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
}
