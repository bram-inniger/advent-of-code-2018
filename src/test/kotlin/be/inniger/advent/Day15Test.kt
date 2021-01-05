package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    private val input = readInputFile("15")

    @Test
    fun validateFirstSampleInputs() {
        val area1 = listOf(
            "#######",
            "#.G...#",
            "#...EG#",
            "#.#.#G#",
            "#..G#E#",
            "#.....#",
            "#######"
        )
        val area2 = listOf(
            "#######",
            "#G..#E#",
            "#E#E.E#",
            "#G.##.#",
            "#...#E#",
            "#...E.#",
            "#######"
        )
        val area3 = listOf(
            "#######",
            "#E..EG#",
            "#.#G.E#",
            "#E.##E#",
            "#G..#.#",
            "#..E#.#",
            "#######"
        )
        val area4 = listOf(
            "#######",
            "#E.G#.#",
            "#.#G..#",
            "#G.#.G#",
            "#G..#.#",
            "#...E.#",
            "#######"
        )
        val area5 = listOf(
            "#######",
            "#.E...#",
            "#.#..G#",
            "#.###.#",
            "#E#G#G#",
            "#...#G#",
            "#######"
        )
        val area6 = listOf(
            "#########",
            "#G......#",
            "#.E.#...#",
            "#..##..G#",
            "#...##..#",
            "#...#...#",
            "#.G...G.#",
            "#.....G.#",
            "#########"
        )

        assertEquals(27_730, Day15.solveFirst(area1))
        assertEquals(36_334, Day15.solveFirst(area2))
        assertEquals(39_514, Day15.solveFirst(area3))
        assertEquals(27_755, Day15.solveFirst(area4))
        assertEquals(28_944, Day15.solveFirst(area5))
        assertEquals(18_740, Day15.solveFirst(area6))
    }

    // FIXME
//    @Test
//    fun validateFirstSolution() {
//        assertEquals(???, Day15.solveFirst(input))
//    }
}
