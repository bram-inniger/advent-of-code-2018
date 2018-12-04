package be.inniger.advent

import be.inniger.advent.Day04.RecordType.*
import org.pcollections.HashTreePMap
import org.pcollections.PMap
import org.pcollections.PVector
import org.pcollections.TreePVector
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

    private fun toRecords(rawRecords: List<String>) = TreePVector.from(
        rawRecords.map { record ->
            values()
                .filter { it.matchesRecord(record) }
                .map { it.parseRecord(record) }
                .single()
        }.sortedBy { it.timestamp })

    private tailrec fun toMinutesPerGuard(
        records: PVector<Record>,
        currentGuard: Int = -1,
        sleepStarted: Int = -1,
        minutesPerGuard: PMap<Int, PMap<Int, Int>> = HashTreePMap.empty()
    ): PMap<Int, PMap<Int, Int>> {
        if (records.isEmpty()) return minutesPerGuard

        val record = records[0]
        val newRecords = records.subList(1, records.size)

        return when (record.recordType) {
            SHIFT_START -> {
                val newGuardId = record.guardId!!
                toMinutesPerGuard(newRecords, newGuardId, sleepStarted, minutesPerGuard)
            }
            ASLEEP -> {
                val newSleepStarted = record.timestamp.minute
                toMinutesPerGuard(newRecords, currentGuard, newSleepStarted, minutesPerGuard)
            }
            AWAKEN -> {
                val sleepEnded = record.timestamp.minute
                val existingMinutes = minutesPerGuard[currentGuard] ?: HashTreePMap.empty()
                val addedMinutes = (sleepStarted until sleepEnded).associate { it to 1 }
                val allMinuteKeys = existingMinutes.keys.union(addedMinutes.keys)
                val newMinutes =
                    allMinuteKeys.associate { it to (existingMinutes[it] ?: 0) + (addedMinutes[it] ?: 0) }
                val newMinutesPerGuard =
                    minutesPerGuard.minus(currentGuard).plus(currentGuard, HashTreePMap.from(newMinutes))
                toMinutesPerGuard(newRecords, currentGuard, sleepStarted, newMinutesPerGuard)
            }
        }
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
