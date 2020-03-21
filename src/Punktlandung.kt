object Punktlandung {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Punktlandung")

        println(enumerate2of8.size)
        enumerate2of8.forEach { println(it) }
    }

    val enumerate2of8: List<List<Boolean>> by lazy {
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
}