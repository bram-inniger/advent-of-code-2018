package be.inniger.advent

import be.inniger.advent.Day04.RecordType.*
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
        .map { record ->
            RecordType.values().filter { it.matchesRecord(record) }.map { it.parseRecord(record) }.single()
        }
        .sortedBy { it.timestamp }


    private fun toMinutesPerGuard(records: List<Record>): Map<Int, Map<Int, Int>> {
        val minutesPerGuard: MutableMap<Int, MutableMap<Int, Int>> =
            records.map { it.guardId }
                .filter { it != null }
                .associate { it!! to mutableMapOf<Int, Int>() }
                .toMutableMap()
        var currentGuard: Int = -1
        var sleepStarted: Int = -1

        records.forEach { (timestamp, recordType, guardId) ->
            when (recordType) {
                // FIXME make Record interface and have multiple data class types
                SHIFT_START -> currentGuard = guardId!!
                ASLEEP -> sleepStarted = timestamp.minute
                AWAKEN -> {
                    val sleepEnded = timestamp.minute
                    (sleepStarted until sleepEnded)
                        .forEach { minute -> minutesPerGuard[currentGuard]!!.merge(minute, 1) { t, u -> t + u } }
                }
            }
        }

        return minutesPerGuard
    }

    private data class Record(val timestamp: LocalDateTime, val recordType: RecordType, val guardId: Int? = null)

    private enum class RecordType(val regex: Regex) {
        SHIFT_START("""^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] Guard #(\d+) begins shift$""".toRegex()) {
            override fun parseRecord(record: String) = Record(
                LocalDateTime.parse(find(record).component1(), formatter), this, find(record).component2().toInt()
            )
        },
        ASLEEP("""^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] falls asleep$""".toRegex()) {
            override fun parseRecord(record: String) =
                Record(LocalDateTime.parse(find(record).component1(), formatter), this)
        },
        AWAKEN("""^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] wakes up$""".toRegex()) {
            override fun parseRecord(record: String) =
                Record(LocalDateTime.parse(find(record).component1(), formatter), this)
        };

        internal val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")!!

        fun matchesRecord(record: String): Boolean = regex.matches(record)
        abstract fun parseRecord(record: String): Record
        internal fun find(record: String): MatchResult.Destructured = regex.find(record)!!.destructured
    }
}
