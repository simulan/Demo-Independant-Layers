package be.simulan.reddit_demo.thread.views

enum class ScoreGradient(val upperBound: Long, val colors: IntArray) {
    blue1(5L, intArrayOf(0xFF3d6883.toInt(), 0xFFaccbe9.toInt())),
    blue2(10L,intArrayOf(0xFF3d6883.toInt(), 0xFF8ebfe7.toInt())),
    blue3(30L,intArrayOf(0xFF3d6883.toInt(), 0xFF67baf4.toInt())),
    blue4(60L,intArrayOf(0xFF3d6883.toInt(), 0xFF2078b4.toInt())),
    blue5(120L,intArrayOf(0xFF3d6883.toInt(), 0xFF0d85d7.toInt()));
    companion object {
        fun get(score : Long) : ScoreGradient = when{
            score< blue1.upperBound -> blue1
            score< blue2.upperBound -> blue2
            score< blue3.upperBound -> blue3
            score< blue4.upperBound -> blue4
            else -> blue5
        }
    }
}