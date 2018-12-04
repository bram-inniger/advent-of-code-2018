package be.inniger.advent

class Day03 {

    private val regex = """#\d+ @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

    fun solveFirst(claims: List<String>) =
        claims.flatMap(::claimToPoints)
            .groupingBy { it }
            .eachCount()
            .map { it.value }
            .count { it > 1 }


    private fun claimToPoints(claim: String): List<Pair<Int, Int>> {
        val (fromLeft, fromTop, width, height) = regex.find(claim)!!.destructured

        return (1..width.toInt()).map { it + fromLeft.toInt() }
            .flatMap { x -> (1..height.toInt()).map { it + fromTop.toInt() }.map { y -> x to y } }
    }
}
