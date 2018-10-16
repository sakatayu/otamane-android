package com.fefeyo.otamanekai

import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.Clock
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `同じ日かどうか`() {
        val sample = LocalDateTime.of(0, 0, 0, 13, 30)
        print(sample.toEpochSecond(ZoneOffset.UTC))
    }
}
