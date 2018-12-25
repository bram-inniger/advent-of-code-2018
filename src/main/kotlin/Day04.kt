package be.inniger.advent

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Day04 {

    fun solveFirst(rawRecords: List<String>): Int {
        val minutesPerGuard = toMinutesPerGuard(toRecords(rawRecords))

        val sleepiestGuard = minutesPerGuard.maxBy { it.value.map { entry -> entry.value }.sum() }!!.key
        val sleepiestMinute = minutesPerGuard[sleepiestGuard]!!.maxBy { it.value }!!.key

        return sleepiestGuard * sleepiestMinute
    }

    fun solveSecond(rawRecords: List<String>): Int {
        val sleepiestGuardByMinute = toMinutesPerGuard(toRecords(rawRecords))
            .map { entry ->
                entry.key to (entry.value
                    .maxBy { minute -> minute.value })
            }
            .maxBy { it.second?.value ?: 0 }!!

        return sleepiestGuardByMinute.first * sleepiestGuardByMinute.second!!.key
    }

    private fun toRecords(rawRecords: List<String>) = rawRecords
        .map(Record.Companion::parseRecord)
        .sortedBy { it.timestamp }

    private fun toMinutesPerGuard(records: List<Record>): Map<Int, Map<Int, Int>> {
        fun sum() = { a: Int, b: Int -> a + b }

        val minutesPerGuard: MutableMap<Int, MutableMap<Int, Int>> =
            records
                .filter { it is ShiftStartRecord }
                .map { it as ShiftStartRecord }
                .map { it.guardId }
                .associate { it to mutableMapOf<Int, Int>() }
                .toMutableMap()
        var currentGuard: Int = -1
        var sleepStarted: Int = -1

        records.forEach {
            when (it) {
                is ShiftStartRecord -> currentGuard = it.guardId
                is AsleepRecord -> sleepStarted = it.timestamp.minute
                is AwakenRecord -> {
                    val sleepEnded = it.timestamp.minute
                    (sleepStarted until sleepEnded)
                        .forEach { minute -> minutesPerGuard[currentGuard]!!.merge(minute, 1, sum()) }
                }
            }
        }

        return minutesPerGuard
    }

    private interface Record {
        companion object {
            private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")!!

            fun parseRecord(record: String) = when {
                ShiftStartRecord.matches(record) -> ShiftStartRecord.parseFrom(record)
                AsleepRecord.matches(record) -> AsleepRecord.parseFrom(record)
                AwakenRecord.matches(record) -> AwakenRecord.parseFrom(record)
                else -> throw IllegalArgumentException()
            }

            fun parseTimestamp(regex: Regex, record: String): LocalDateTime =
                LocalDateTime.parse(regex.find(record)!!.destructured.component1(), formatter)

            fun parseGuardId(regex: Regex, record: String) =
                regex.find(record)!!.destructured.component2().toInt()
        }

        val timestamp: LocalDateTime
            get() = LocalDateTime.MIN
    }

    private data class ShiftStartRecord(override val timestamp: LocalDateTime, val guardId: Int) : Record {
        companion object {
            fun matches(record: String) = regex.matches(record)
            fun parseFrom(record: String) = ShiftStartRecord(
                Record.parseTimestamp(regex, record), Record.parseGuardId(regex, record)
            )

            val regex = """^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] Guard #(\d+) begins shift$""".toRegex()
        }
    }

    private data class AsleepRecord(override val timestamp: LocalDateTime) : Record {
        companion object {
            fun matches(record: String) = regex.matches(record)
            fun parseFrom(record: String) = AsleepRecord(Record.parseTimestamp(regex, record))
            val regex = """^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] falls asleep$""".toRegex()
        }
    }

    private data class AwakenRecord(override val timestamp: LocalDateTime) : Record {
        companion object {
            fun matches(record: String) = regex.matches(record)
            fun parseFrom(record: String) = AwakenRecord(Record.parseTimestamp(regex, record))
            val regex = """^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] wakes up$""".toRegex()
        }
    }
}
