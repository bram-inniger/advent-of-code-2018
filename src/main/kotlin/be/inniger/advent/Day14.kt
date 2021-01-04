package be.inniger.advent

object Day14 {

    fun solveFirst(nrTries: Int): String {
        val recipes = mutableListOf(3, 7)
        var firstElf = 0
        var secondElf = 1

        while (recipes.size < nrTries + 10) {
            val newRecipe = recipes[firstElf] + recipes[secondElf]

            if (newRecipe > 9) {
                recipes.add(newRecipe / 10)
                recipes.add(newRecipe % 10)
            } else {
                recipes.add(newRecipe)
            }

            firstElf = moveElf(firstElf, recipes)
            secondElf = moveElf(secondElf, recipes)
        }

        return (nrTries until nrTries + 10)
            .joinToString("") { recipes[it].toString() }
    }

    fun solveSecond(recipe: String): Int {
        val toFind = recipe.toCharArray().map { it.toString() }.map { it.toInt() }
        val recipes = mutableListOf(3, 7)
        var firstElf = 0
        var secondElf = 1

        while (true) {
            val newRecipe = recipes[firstElf] + recipes[secondElf]

            if (newRecipe > 9) {
                recipes.add(newRecipe / 10)
                recipes.add(newRecipe % 10)

                if (containsRecipe(toFind, recipes, recipes.lastIndex)) return recipes.lastIndex - toFind.size
                if (containsRecipe(toFind, recipes, recipes.lastIndex - 1)) return recipes.lastIndex - 1 - toFind.size
            } else {
                recipes.add(newRecipe)

                if (containsRecipe(toFind, recipes, recipes.lastIndex)) return recipes.lastIndex - toFind.size
            }

            firstElf = moveElf(firstElf, recipes)
            secondElf = moveElf(secondElf, recipes)
        }
    }

    private fun moveElf(elf: Int, recipes: List<Int>) = (1 + elf + recipes[elf]) % recipes.size

    private fun containsRecipe(toFind: List<Int>, recipes: List<Int>, index: Int): Boolean {
        if (index < toFind.size) return false

        return toFind.indices
            .all { toFind[it] == recipes[it + index - toFind.size] }
    }
}
