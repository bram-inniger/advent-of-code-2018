package be.inniger.advent

class Day03 {

    companion object {
        private val regex = """^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$""".toRegex()
    }

    fun solveFirst(claims: List<String>) = groupClaimsByPointPositions(claims).count { it.size > 1 }

    fun solveSecond(claims: List<String>) =
        findAllClaimIds(groupClaimsByPointPositions(claims), 0)
            .minus(findAllClaimIds(groupClaimsByPointPositions(claims), 1))
            .single()

    private fun groupClaimsByPointPositions(claims: List<String>) =
        claims.flatMap(::pointsFromClaim)
            .groupBy { "${it.x},${it.y}" }
            .values

    private fun pointsFromClaim(claim: String): List<Point> {
        val (claimId, fromLeft, fromTop, width, height) = regex.find(claim)!!.destructured

        return (1..width.toInt()).map { it + fromLeft.toInt() }
            .flatMap { x -> (1..height.toInt()).map { it + fromTop.toInt() }.map { y -> x to y } }
            .map { Point(claimId, it.first, it.second) }
    }

    private fun findAllClaimIds(groupedPoints: Collection<List<Point>>, nrOverlaps: Int) =
        groupedPoints.filter { it.size > nrOverlaps }
            .flatMap { it.map { point -> point.claimId } }
            .toSet()

    private data class Point(val claimId: String, val x: Int, val y: Int)
}
