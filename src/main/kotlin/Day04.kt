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
        val minutesPerGuard = toMinutesPerGuard(toRecords(rawRecords))

        val sleepiestGuardByMinute = minutesPerGuard
            .map { entry ->
                entry.key to (entry.value
                    .maxBy { minute -> minute.value })
            }
            .filter { it.second != null }
            .maxBy { it.second!!.value }!!


        return sleepiestGuardByMinute.first * sleepiestGuardByMinute.second!!.key
    }

    private fun toRecords(rawRecords: List<String>) = rawRecords.map { record ->
        RecordType.values()
            .filter { it.matchesRecord(record) }
            .map { it.parseRecord(record) }
            .single()
    }.sortedBy { it.timestamp }

    private fun toMinutesPerGuard(records: List<Record>): HashMap<Int, HashMap<Int, Int>> {
        var currentGuard = -1 // will be overwritten and prevents nullability
        var sleepStarted = -1 // will be overwritten and prevents nullability

        val guardMinutes = HashMap<Int, HashMap<Int, Int>>()

        for (record in records) when (record.recordType) {
            SHIFT_START -> currentGuard = record.guardId!!
            ASLEEP -> sleepStarted = record.timestamp.minute
            AWAKEN -> {
                val sleepEnded = record.timestamp.minute
                (sleepStarted until sleepEnded).forEach {
                    guardMinutes.computeIfAbsent(currentGuard) { HashMap() }
                        .merge(it, 1) { u, _ -> u + 1 }
                }
            }
        }

        return guardMinutes
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
