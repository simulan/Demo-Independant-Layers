package be.sanderdebleecker.reddit_demo

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ArrayListTest {
    @Test
    fun trimStart() {
        // arrange
        val arr : ArrayList<String> = arrayListOf("Tom","Helen","Dunley")
        val sizeA = arr.size
        // act
        arr.removeAt(0)
        var sizeB = arr.size
        // assert
        assert(sizeA!=sizeB)
    }
}
