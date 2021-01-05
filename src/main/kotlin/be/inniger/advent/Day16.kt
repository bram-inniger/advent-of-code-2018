package be.inniger.advent

object Day16 {

    fun solveFirst(samples: String) =
        samples.split("\n\n\n")[0]
            .split("\n\n")
            .map { Sample.of(it) }
            .map { findMatchingOpcodes(it) }
            .filter { it.size >= 3 }
            .count()

    private fun findMatchingOpcodes(sample: Sample) =
        Opcode.values()
            .filter { it.operation(sample.before, sample.instr[1], sample.instr[2], sample.instr[3]) == sample.after }

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
