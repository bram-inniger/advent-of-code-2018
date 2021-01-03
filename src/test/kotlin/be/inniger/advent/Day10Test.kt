package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    private val input = readInputFile("10")
    private val pointDescriptions = listOf(
        "position=< 9,  1> velocity=< 0,  2>",
        "position=< 7,  0> velocity=<-1,  0>",
        "position=< 3, -2> velocity=<-1,  1>",
        "position=< 6, 10> velocity=<-2, -1>",
        "position=< 2, -4> velocity=< 2,  2>",
        "position=<-6, 10> velocity=< 2, -2>",
        "position=< 1,  8> velocity=< 1, -1>",
        "position=< 1,  7> velocity=< 1,  0>",
        "position=<-3, 11> velocity=< 1, -2>",
        "position=< 7,  6> velocity=<-1, -1>",
        "position=<-2,  3> velocity=< 1,  0>",
        "position=<-4,  3> velocity=< 2,  0>",
        "position=<10, -3> velocity=<-1,  1>",
        "position=< 5, 11> velocity=< 1, -2>",
        "position=< 4,  7> velocity=< 0, -1>",
        "position=< 8, -2> velocity=< 0,  1>",
        "position=<15,  0> velocity=<-2,  0>",
        "position=< 1,  6> velocity=< 1,  0>",
        "position=< 8,  9> velocity=< 0, -1>",
        "position=< 3,  3> velocity=<-1,  1>",
        "position=< 0,  5> velocity=< 0, -1>",
        "position=<-2,  2> velocity=< 2,  0>",
        "position=< 5, -2> velocity=< 1,  2>",
        "position=< 1,  4> velocity=< 2,  1>",
        "position=<-2,  7> velocity=< 2, -2>",
        "position=< 3,  6> velocity=<-1, -1>",
        "position=< 5,  0> velocity=< 1,  0>",
        "position=<-6,  0> velocity=< 2,  0>",
        "position=< 5,  9> velocity=< 1, -2>",
        "position=<14,  7> velocity=<-2,  0>",
        "position=<-3,  6> velocity=< 2, -1>"
    )

    @Test
    fun validateFirstSampleInputs() {
        val print =
            """
                #...#..###
                #...#...#.
                #...#...#.
                #####...#.
                #...#...#.
                #...#...#.
                #...#...#.
                #...#..###
            """.trimIndent()

        assertEquals(print, Day10.solveFirst(pointDescriptions))
    }

    @Test
    fun validateFirstSolution() {
        val print =
            """
                #....#..######..#....#...####...#####......###...####...######
                #...#........#..#....#..#....#..#....#......#...#....#.......#
                #..#.........#..#....#..#.......#....#......#...#............#
                #.#.........#...#....#..#.......#....#......#...#...........#.
                ##.........#....######..#.......#####.......#...#..........#..
                ##........#.....#....#..#..###..#..#........#...#..###....#...
                #.#......#......#....#..#....#..#...#.......#...#....#...#....
                #..#....#.......#....#..#....#..#...#...#...#...#....#..#.....
                #...#...#.......#....#..#...##..#....#..#...#...#...##..#.....
                #....#..######..#....#...###.#..#....#...###.....###.#..######
            """.trimIndent()

        assertEquals(print, Day10.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(3, Day10.solveSecond(pointDescriptions))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(10_932, Day10.solveSecond(input))
    }
}
