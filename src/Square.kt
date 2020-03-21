
class Square {
    val fields: Array<Array<Boolean>> = Array(8) { Array(8) { false } }

    fun get(i: Int, j: Int) = fields[i][j]

    fun set(i: Int, j: Int) { fields[i][j] = true }
}