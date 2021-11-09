import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class SampleTest {
    private val testSample: Sample = Sample()

    @Test
    fun testSample() {
        val expected = true
        assertEquals(expected, testSample.isTrue())
    }
}