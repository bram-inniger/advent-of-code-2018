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

    private fun moveElf(elf: Int, recipes: List<Int>) = (1 + elf + recipes[elf]) % recipes.size
}
