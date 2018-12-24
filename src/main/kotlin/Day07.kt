package be.inniger.advent

class Day07 {

    fun solveFirst(requirements: List<String>): String {
        val dependenciesList = requirements.map(::Dependency)
        
        val dependencies = dependenciesList
            .groupBy(Dependency::beforeStep, Dependency::afterStep)
            .entries
            .associate { it.key to it.value.toMutableSet() }
            .toMutableMap()
        
        dependenciesList.map { it.afterStep }.forEach {dependencies.putIfAbsent(it, mutableSetOf())}
        
        return solveRec(dependencies, "")
    }

    private tailrec fun solveRec(dependencies: MutableMap<Char, MutableSet<Char>>, path: String): String =
        if (dependencies.isEmpty()) path
        else {
            val unavailableSteps = dependencies.values.flatten().distinct()
            val availableSteps = dependencies.keys.minus(unavailableSteps)
            val nextStep = availableSteps.min()
            dependencies.remove(nextStep)
            
            solveRec(dependencies, path + nextStep)
        }

    private class Dependency internal constructor(requirement: String) {
        internal val beforeStep: Char
        internal val afterStep: Char

        private val regex = """^Step (\w) must be finished before step (\w) can begin\.$""".toRegex()

        init {
            val (beforeStep, afterStep) = regex.find(requirement)!!.destructured

            this.beforeStep = beforeStep.single()
            this.afterStep = afterStep.single()
        }
    }
}
