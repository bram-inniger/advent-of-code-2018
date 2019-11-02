package be.inniger.advent.util

fun readInputFile(day: String) = Thread.currentThread()
    .contextClassLoader
    .getResourceAsStream("inputs/$day.txt")!!
    .reader()
    .readLines()
