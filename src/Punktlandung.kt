object Punktlandung {

    /**
    1:
    (0, 3)
    (2, 4)
    (2, 6)
    (1, 7)
    (5, 6)
    (1, 7)
    (3, 5)
    (0, 4)
     */

    @JvmStatic
    fun main(args: Array<String>) {
        println("Punktlandung")

        println(enumerate2of8.size)

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

                                                    println(row1)
                                                    println(row2)
                                                    println(row3)
                                                    println(row4)
                                                    println(row5)
                                                    println(row6)
                                                    println(row7)
                                                    println(row8)
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

    private fun pointsOnLine(point1: Pair<Int, Int>, point2: Pair<Int, Int>, point3: Pair<Int, Int>): Boolean {
        val diffH1 = point2.second - point1.second
        val diffH2 = point3.second - point2.second

        val diffV1 = point2.first - point1.first
        val diffV2 = point3.first - point2.first

        val prop1: Double = diffH1.toDouble() / diffV1.toDouble()
        val prop2: Double = diffH2.toDouble() / diffV2.toDouble()

        return prop1 == prop2
    }

    private fun checkDiagonalSingle(givenRows: Array<Pair<Int, Int>>, candidate: Pair<Int, Int>, rowIndex0: Int, rowIndex1: Int): Boolean {
        val candidateIndex = givenRows.size

        val row0 = givenRows[rowIndex0]
        val row1 = givenRows[rowIndex1]

        val candidatePoint1 = Pair(candidate.first, candidateIndex)
        val candidatePoint2 = Pair(candidate.second, candidateIndex)

        val point01 = Pair(row0.first, rowIndex0)
        val point02 = Pair(row0.second, rowIndex0)

        val point11 = Pair(row1.first, rowIndex1)
        val point12 = Pair(row1.second, rowIndex1)

        return !pointsOnLine(point01, point11, candidatePoint1) &&
                !pointsOnLine(point02, point11, candidatePoint1) &&
                !pointsOnLine(point01, point12, candidatePoint1) &&
                !pointsOnLine(point02, point12, candidatePoint1) &&
                !pointsOnLine(point01, point11, candidatePoint2) &&
                !pointsOnLine(point02, point11, candidatePoint2) &&
                !pointsOnLine(point01, point12, candidatePoint2) &&
                !pointsOnLine(point02, point12, candidatePoint2)
    }

    private fun checkDiagonals(givenRows: Array<Pair<Int, Int>>, candidate: Pair<Int, Int>): Boolean {
        for (index0 in 0 .. givenRows.size-1) {
            for (index1 in index0+1 .. givenRows.size-1) {
                if (!checkDiagonalSingle(givenRows, candidate, index0, index1)) {
                    return false
                }
            }
        }
        return true
    }

    // 1. only 2 in column        : 3508230
    // 2. diagonals of rows 1 % 2 : 1598002
    // 3. diagonals of all rows   : 1

    fun legal(vararg givenRows: Pair<Int, Int>): List<Pair<Int, Int>> {
        return enumerate2of8.filter {
            val atPos0: Int = givenRows.count { row -> row.first == it.first || row.second == it.first }
            val atPos1: Int = givenRows.count { row -> row.second == it.second || row.first == it.second }

            atPos0 < 2 && atPos1 < 2 && checkDiagonals(givenRows as Array<Pair<Int, Int>>, it)
        }
    }
}