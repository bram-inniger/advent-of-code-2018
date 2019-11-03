package be.inniger.advent

import java.util.SortedSet
import java.util.TreeSet

class Day07 {

    fun solveFirst(requirements: List<String>): String {
        val dependenciesList = requirements.map { Dependency.from(it) }

        val dependencies = dependenciesList
            .groupBy(Dependency::afterStep, Dependency::beforeStep)
            .entries
            .map { it.key to it.value.toMutableSet() }
            .toMap()
            .toMutableMap()

        val result = StringBuilder()
        val availableSteps: SortedSet<Char> = TreeSet(findParentSteps(dependencies))

        while (availableSteps.isNotEmpty()) {
            val currentStep = availableSteps.first() // First step is the first one coming in the alphabet

            dependencies.values.forEach { it.remove(currentStep) } // Remove this dependency from all other steps
            val newlyAvailableSteps = dependencies.entries // Check which new steps now become available
                .filter { it.value.isEmpty() }
                .map { it.key }
            availableSteps.remove(currentStep) // Remove the currently taken step
            availableSteps.addAll(newlyAvailableSteps) // Add all newly available steps
            newlyAvailableSteps.forEach { dependencies.remove(it) } // Remove now empty steps not to get scheduled again

            result.append(currentStep)
        }

        return result.toString()
    }

    private fun findParentSteps(dependencies: MutableMap<Char, MutableSet<Char>>): Set<Char> {
        val prerequisiteSteps = dependencies.values
            .flatten()
            .toSet()
        val dependentSteps = dependencies.keys

        // All steps required to happen before another, minus the ones that themselves have requirements
        val parentSteps = prerequisiteSteps.subtract(dependentSteps)

        return if (parentSteps.isNotEmpty()) parentSteps
        else throw NoSuchElementException()
    }

    private data class Dependency(val beforeStep: Char, val afterStep: Char) {
        companion object {
            private val regex = """^Step (\w) must be finished before step (\w) can begin\.$""".toRegex()

            internal fun from(requirement: String): Dependency {
                val (beforeStep, afterStep) = regex.find(requirement)!!.destructured
                return Dependency(beforeStep.single(), afterStep.single())
            }
        }
    }
}
