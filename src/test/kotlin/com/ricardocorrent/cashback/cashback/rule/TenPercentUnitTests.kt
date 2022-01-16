package com.ricardocorrent.cashback.cashback.rule

import com.ricardocorrent.cashback.utils.randomValueUntil
import com.ricardocorrent.cashback.utils.scaled
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TenPercentUnitTests {

    @Test
    fun `it should return the next cash back calculator when asked for the next cash back`() {
        // scenario
        val expectedNext = FifteenPercent::class.java

        // execution
        val response = TenPercent.next::class.java

        // validation
        Assertions.assertTrue { expectedNext == response }
    }

    @Test
    fun `it should return expected percentage when asked to get percentage used to calculate cash back`() {
        // scenario
        val expectedPercentage = BigDecimal.valueOf(0.10)

        // execution
        val response = TenPercent.percentage

        // validation
        Assertions.assertTrue { expectedPercentage == response }
    }

    @Test
    fun `it should calculate cash back when asked for do it`() {
        // scenario
        val value = randomValueUntil(BigDecimal.valueOf(1000))
        val percentage = BigDecimal.valueOf(0.10)
        val expectedCashBack = value.multiply(percentage).scaled()

        // execution
        val (_, cashBack) = TenPercent.calculateCashBackValue(value)

        // validation
        Assertions.assertTrue { expectedCashBack == cashBack }
    }

    @Test
    fun `it should calculate with fifteen percent of cash back when the value is more than 1000`() {
        // scenario
        val value = BigDecimal.valueOf(1500)
        val expectedPercentage = BigDecimal.valueOf(0.15)

        // execution
        val (percentage, _) = TenPercent.calculateCashBackValue(value)

        // validation
        Assertions.assertEquals(expectedPercentage, percentage)
    }

}
