import kotlin.test.*


internal class SampleTest {
    private val testSample: Sample = Sample()

    @Test
    fun testSample() {
        val expected = true
        assertEquals(expected, testSample.isTrue())
    }
}