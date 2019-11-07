package be.inniger.advent

import java.util.SortedSet
import java.util.TreeSet

class Day07 {

    fun solveFirst(requirements: List<String>) =
        solve(requirements).sequence

    fun solveSecond(requirements: List<String>, nrWorkers: Int, timeAddition: Int) =
        solve(requirements, nrWorkers, timeAddition).time

    private fun solve(requirements: List<String>, nrWorkers: Int = 1, timeAddition: Int = 0): Result {
        val dependenciesList = requirements.map { Dependency.from(it) }

        val dependencies = dependenciesList
            .groupBy(Dependency::afterStep, Dependency::beforeStep)
            .entries
            .map { it.key to it.value.toMutableSet() }
            .toMap()
            .toMutableMap()

        val availableSteps: SortedSet<Char> = TreeSet(findParentSteps(dependencies))
        val activeTasks = mutableSetOf<Task>()
        val sequence = StringBuilder()
        var time = 0

        while (availableSteps.isNotEmpty() || activeTasks.isNotEmpty()) {
            // Clean up finished tasks
            val doneTasks = activeTasks.filter { it.hasFinished(time) }
            activeTasks.removeAll(doneTasks)
            doneTasks.forEach { resolveStep(it.step, dependencies, availableSteps) }
            doneTasks.forEach { sequence.append(it.step) }

            // Put available workers to work on open tasks
            val availableWorkers = nrWorkers - activeTasks.size
            for (i in 1..availableWorkers) {
                if (availableSteps.isNotEmpty()) {
                    val step = availableSteps.first()
                    val task = Task.from(step, time, timeAddition)

                    availableSteps.remove(step)
                    activeTasks.add(task)
                }
            }

            time++
        }

        return Result(sequence.toString(), time - 1)
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

    private fun resolveStep(
        step: Char,
        dependencies: MutableMap<Char, MutableSet<Char>>,
        availableSteps: SortedSet<Char>
    ) {
        dependencies.values.forEach { it.remove(step) } // Remove this dependency from all other steps
        val newlyAvailableSteps = dependencies.entries // Check which new steps now become available
            .filter { it.value.isEmpty() }
            .map { it.key }
        availableSteps.remove(step) // Remove the currently taken step
        availableSteps.addAll(newlyAvailableSteps) // Add all newly available steps
        newlyAvailableSteps.forEach { dependencies.remove(it) } // Remove now empty steps not to get scheduled again
    }

    private data class Dependency(val beforeStep: Char, val afterStep: Char) {
        companion object {
            val regex = """^Step (\w) must be finished before step (\w) can begin\.$""".toRegex()

            fun from(requirement: String): Dependency {
                val (beforeStep, afterStep) = regex.find(requirement)!!.destructured
                return Dependency(beforeStep.single(), afterStep.single())
            }
        }
    }

    private data class Task(val step: Char, val endTime: Int) {
        fun hasFinished(currentTime: Int) = currentTime >= endTime

        companion object {
            fun from(step: Char, startTime: Int, timeAddition: Int) =
                Task(step, calculateEndTime(step, startTime, timeAddition))

            fun calculateEndTime(step: Char, startTime: Int, timeAddition: Int) =
                step - 'A' + 1 + startTime + timeAddition
        }
    }

    private data class Result(val sequence: String, val time: Int)
}
