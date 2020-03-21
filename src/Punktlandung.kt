object Punktlandung {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Punktlandung")

        println(enumerate2of8.size)
        // enumerate2of8.forEach { println(it) }

        var count = 0;
        for (row1 in enumerate2of8) {
            for (row2 in enumerate2of8) {
                for (row3 in legal(row1, row2)) {
                    if (row3.first == 2 || row3.second == 2) {
                        for (row4 in legal(row1, row2, row3)) {
                            if (row4.first == 1 || row4.second == 1) {
                                for (row5 in legal(row1, row2, row3, row4)) {
                                    for (row6 in legal(row1, row2, row3, row4, row5)) {
                                        for (row7 in legal(row1, row2, row3, row4, row5, row6)) {
                                            for (row8 in legal(row1, row2, row3, row4, row5, row6, row7)) {
                                                if (row8.first == 4 || row8.second == 4 ) {
                                                    count++
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        println(count)
    }

    private val enumerate2of8: List<Pair<Int, Int>> by lazy {
        val result = mutableListOf<Pair<Int, Int>>()
        for (pos0 in 0 .. 7) {
            for (pos1 in pos0+1 .. 7) {
                result.add(Pair(pos0, pos1))
            }
        }
        result.toList()
    }

    private fun checkDiagonals(givenRows: Array<Pair<Int, Int>>, candidate: Pair<Int, Int>, rowIndex0: Int, rowIndex1: Int): Boolean {
        val row1 = givenRows[rowIndex0]
        val row2 = givenRows[rowIndex1]
        val index = givenRows.size

        val forbidden1 = (row2.first - row1.first) * (index - 1) + row2.first
        val forbidden2 = (row2.second - row1.first) * (index - 1) + row2.second
        val forbidden3 = (row2.first - row1.second) * (index - 1) + row2.first
        val forbidden4 = (row2.second - row1.second) * (index - 1) + row2.second

        return candidate.first != forbidden1 && candidate.first != forbidden2 && candidate.first != forbidden3 && candidate.first != forbidden4 &&
            candidate.second != forbidden1 && candidate.second != forbidden2 && candidate.second != forbidden3 && candidate.second != forbidden4
    }

    // 1. only 2 in column        : 3508230
    // 2. diagonals of rows 1 % 2 : 1598002
    // 3. diagonals of all rows   :
    fun legal(vararg givenRows: Pair<Int, Int>): List<Pair<Int, Int>> {
        return enumerate2of8.filter {
            val atPos0: Int = givenRows.count { row -> row.first == it.first || row.second == it.first }
            val atPos1: Int = givenRows.count { row -> row.second == it.second || row.first == it.second }

            if (atPos0 < 2 && atPos1 < 2) {
                checkDiagonals(givenRows as Array<Pair<Int, Int>>, it, 0, 1)
            } else {
                false
            }
        }
    }
}