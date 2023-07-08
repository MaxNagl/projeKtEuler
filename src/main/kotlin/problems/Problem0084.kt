package problems

import kotlin.random.Random

fun main() = runProblem()

class Problem0084 : Problem(101524, 10) {
    override fun calc(): Any {
        val fields = listOf(
            "GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3",
            "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3",
            "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
            "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"
        )
        val fieldGO = fields.indexOf("GO")
        val fieldJAIL = fields.indexOf("JAIL")
        val fieldG2J = fields.indexOf("G2J")
        val fieldCC1 = fields.indexOf("CC1")
        val fieldCC2 = fields.indexOf("CC2")
        val fieldCC3 = fields.indexOf("CC3")
        val fieldCH1 = fields.indexOf("CH1")
        val fieldCH2 = fields.indexOf("CH2")
        val fieldCH3 = fields.indexOf("CH3")
        val fieldU1 = fields.indexOf("U1")
        val fieldU2 = fields.indexOf("U2")

        var posCC = 0
        val cardsCC = listOf(fieldGO, fieldJAIL) + List<Int?>(14) { null }.shuffled()

        var posCH = 0
        val cardsCH = listOf(
            fieldGO, fieldJAIL,
            fields.indexOf("C1"), fields.indexOf("E3"), fields.indexOf("H2"), fields.indexOf("R1"),
            -1, -1, -2, -3
        ) + List<Int?>(6) { null }.shuffled()

        val visits = IntArray(fields.size)
        var pos = 0
        var doubles = 0
        val rounds = 10000000
        repeat(rounds) {
            val d1 = Random.nextInt(4) + 1
            val d2 = Random.nextInt(4) + 1
            pos = (pos + d1 + d2) % fields.size
            if (d1 == d2) {
                if (++doubles == 3) {
                    doubles = 0
                    pos = fieldJAIL
                }
            } else doubles = 0
            while (true) {
                pos = when (pos) {
                    fieldG2J -> fieldJAIL
                    fieldCC1, fieldCC2, fieldCC3 -> cardsCC[posCC++ % cardsCC.size]
                    fieldCH1, fieldCH2, fieldCH3 -> cardsCH[posCH++ % cardsCH.size].let { ch ->
                        when (ch) {
                            -1 -> ((pos + 5) / 10 * 10 + 5) % fields.size
                            -2 -> if (pos == fieldCH2) fieldU2 else fieldU1
                            -3 -> (pos + fields.size - 3) % fields.size
                            else -> ch
                        }
                    }
                    else -> break
                } ?: break
            }
            visits[pos]++
        }
        return visits.mapIndexed { field, v -> field to v }.sortedByDescending { it.second }
            .take(3).map { v -> (v.first + 100).toString().takeLast(2) }.joinToString("")
    }
}