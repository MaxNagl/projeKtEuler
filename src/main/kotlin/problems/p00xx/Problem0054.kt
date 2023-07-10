package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0054 : Problem(376) {
    data class Hand(val cards: List<String>) {
        val values = IntArray(allValues.size)
        val colors = IntArray(allColors.size)

        init {
            cards.forEach {
                values[allValues.indexOf(it[0])]++
                colors[allColors.indexOf(it[1])]++
            }
        }
    }

    enum class Ranks(val best: (Hand) -> Int?) {
        HighCard({ hand ->
            hand.values.indexOfLast { it != 0 }
        }),
        OnePair({ hand ->
            if (hand.values.contains(2)) hand.values.indexOfLast { it == 2 } else null
        }),
        TwoPair({ hand ->
            if (hand.values.count { it == 2 } == 2) hand.values.indexOfLast { it == 2 } else null
        }),
        ThreeOfAKind({ hand ->
            if (hand.values.contains(3)) hand.values.indexOfLast { it == 3 } else null
        }),
        Straight({ hand ->
            if (hand.values.all { it <= 1 } && hand.values.indexOfFirst { it == 1 } + 4 == hand.values.indexOfLast { it == 1 })
                hand.values.indexOfLast { it == 1 } else null
        }),
        Flush({ hand ->
            if (hand.colors.contains(5)) hand.values.indexOfLast { it == 1 } else null
        }),
        FullHouse({ hand ->
            if (hand.values.contains(2) && hand.values.contains(3)) hand.values.indexOfLast { it != 0 } else null
        }),
        FourOfAKind({ hand ->
            if (hand.values.contains(4)) hand.values.indexOfLast { it == 4 } else null
        }),
        StraightFlush({ hand ->
            if (hand.colors.contains(5)) Straight.best(hand) else null
        }),
    }


    override fun calc(): Any {
        val cards = getDataLines { it.split(" ") }
        val ranksInverse = Ranks.values().reversed()

        return cards.count { round ->
            val p1 = Hand(round.take(5))
            val p2 = Hand(round.takeLast(5))
            val p1r = ranksInverse.first { it.best(p1) != null }
            val p2r = ranksInverse.first { it.best(p2) != null }
            p1r.ordinal > p2r.ordinal || (p1r == p2r && p1r.best(p1)!! > p2r.best(p2)!!)

        }
    }

    companion object {
        val allValues = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
        val allColors = listOf('C', 'S', 'H', 'D')
    }
}
