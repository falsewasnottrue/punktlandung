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


    // 1. only 2 in column: 3508230
    // 2. check diagonals:
    fun legal(vararg givenRows: Pair<Int, Int>): List<Pair<Int, Int>> {
        return enumerate2of8.filter {
            val atPos0: Int = givenRows.count { row -> row.first == it.first || row.second == it.first }
            val atPos1: Int = givenRows.count { row -> row.second == it.second || row.first == it.second }

            atPos0 < 2 && atPos1 < 2
        }
    }
}