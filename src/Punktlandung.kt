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
                    if (row3[2]) {
                        for (row4 in legal(row1, row2, row3)) {
                            if (row4[1]) {
                                for (row5 in legal(row1, row2, row3, row4)) {
                                    for (row6 in legal(row1, row2, row3, row4, row5)) {
                                        for (row7 in legal(row1, row2, row3, row4, row5, row6)) {
                                            for (row8 in legal(row1, row2, row3, row4, row5, row6, row7)) {
                                                if (row8[4]) {
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

    private val enumerate2of8: List<List<Boolean>> by lazy {
        val result = mutableListOf<List<Boolean>>()
        for (pos0 in 0 .. 7) {
            for (pos1 in pos0+1 .. 7) {
                val row = mutableListOf<Boolean>()
                for (pos in 0 .. 7) {
                    row.add(pos == pos0 || pos == pos1)
                }

                result.add(row.toList())
            }
        }
        result.toList()
    }

    // FIXME implement
    fun legal(vararg givenRows: List<Boolean>): List<List<Boolean>> {
        return enumerate2of8.filter {
            val pos0: Int = it.indexOfFirst { p -> p }
            val pos1: Int = it.indexOfLast { p -> p }

            val atPos0: Int = givenRows.count { it[pos0] }
            val atPos1: Int = givenRows.count { it[pos1] }

            atPos0 < 2 && atPos1 < 2
        }
    }


}