package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail

object Day16 {

    fun solveFirst(samples: String) =
        samples.split("\n\n\n\n")[0]
            .split("\n\n")
            .map { Sample.of(it) }
            .count { findMatchingOpcodes(it).size >= 3 }

    fun solveSecond(samplesRaw: String): Int {
        val samples = samplesRaw.split("\n\n\n\n")[0]
            .split("\n\n")
            .map { Sample.of(it) }
        val program = samplesRaw.split("\n\n\n\n")[1]
            .split("\n")
            .map { instr -> instr.split(" ").map { code -> code.toInt() } }

        return runProgram(
            program,
            deduceOpcodes(samples),
            listOf(0, 0, 0, 0)
        )[0]
    }

    private fun findMatchingOpcodes(sample: Sample, opcodes: Set<Opcode> = Opcode.values().toSet()) =
        opcodes.filter {
            it.operation(
                sample.before,
                sample.instr[1],
                sample.instr[2],
                sample.instr[3]
            ) == sample.after
        }

    private tailrec fun deduceOpcodes(
        samples: List<Sample>,
        toFind: Set<Opcode> = Opcode.values().toSet(),
        found: Map<Int, Opcode> = mapOf()
    ): Map<Int, Opcode> =
        if (toFind.isEmpty()) found
        else {
            val (instr, opcode) = samples
                .filter { !found.keys.contains(it.instr[0]) }
                .map { it to findMatchingOpcodes(it, toFind) }
                .first { it.second.size == 1 }
                .let { it.first.instr[0] to it.second.single() }

            deduceOpcodes(samples, toFind - opcode, found + (instr to opcode))
        }

    private tailrec fun runProgram(program: List<List<Int>>, opcodes: Map<Int, Opcode>, reg: List<Int>): List<Int> =
        if (program.isEmpty()) reg
        else {
            val instr = program.head()
            val newReg = opcodes[instr[0]]!!.operation(reg, instr[1], instr[2], instr[3])
            runProgram(program.tail(), opcodes, newReg)
        }

    private enum class Opcode(val operation: (reg: List<Int>, inA: Int, inB: Int, outC: Int) -> List<Int>) {
        ADDR({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] + reg[inB]) }),
        ADDI({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] + inB) }),
        MULR({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] * reg[inB]) }),
        MULI({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] * inB) }),
        BANR({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] and reg[inB]) }),
        BANI({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] and inB) }),
        BORR({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] or reg[inB]) }),
        BORI({ reg, inA, inB, outC -> updateReg(reg, outC, reg[inA] or inB) }),
        SETR({ reg, inA, _, outC -> updateReg(reg, outC, reg[inA]) }),
        SETI({ reg, inA, _, outC -> updateReg(reg, outC, inA) }),
        GTIR({ reg, inA, inB, outC -> updateReg(reg, outC, if (inA > reg[inB]) 1 else 0) }),
        GTRI({ reg, inA, inB, outC -> updateReg(reg, outC, if (reg[inA] > inB) 1 else 0) }),
        GTRR({ reg, inA, inB, outC -> updateReg(reg, outC, if (reg[inA] > reg[inB]) 1 else 0) }),
        EQIR({ reg, inA, inB, outC -> updateReg(reg, outC, if (inA == reg[inB]) 1 else 0) }),
        EQRI({ reg, inA, inB, outC -> updateReg(reg, outC, if (reg[inA] == inB) 1 else 0) }),
        EQRR({ reg, inA, inB, outC -> updateReg(reg, outC, if (reg[inA] == reg[inB]) 1 else 0) });

        companion object {
            private fun updateReg(reg: List<Int>, outC: Int, valC: Int) =
                reg.mapIndexed { index, existingVal -> if (index == outC) valC else existingVal }
        }
    }

    private data class Sample(val instr: List<Int>, val before: List<Int>, val after: List<Int>) {

        companion object {
            private val instructionRegex = """^(\d+ \d+ \d+ [0-3])$""".toRegex()
            private val beforeRegex = """^Before: \[(\d, \d, \d, \d)]$""".toRegex()
            private val afterRegex = """^After: {2}\[(\d, \d, \d, \d)]$""".toRegex()

            fun of(sample: String) = sample.split("\n")
                .let {
                    Sample(
                        splitToInts(it[1], instructionRegex),
                        splitToInts(it[0], beforeRegex),
                        splitToInts(it[2], afterRegex)
                    )
                }

            private fun splitToInts(toParse: String, regex: Regex) =
                regex.find(toParse)!!
                    .groupValues[1]
                    .split(" ", ", ")
                    .map { it.toInt() }
        }
    }
}
