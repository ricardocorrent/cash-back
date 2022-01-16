package com.ricardocorrent.cashback.cashback.rule

import com.ricardocorrent.cashback.utils.generateRandomBigDecimalFromRange
import com.ricardocorrent.cashback.utils.randomValueUntil
import com.ricardocorrent.cashback.utils.scaled
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class FifteenPercentUnitTests {

    @Test
    fun `it should return the next cash back calculator when asked for the next cash back`() {
        // scenario
        val expectedNext = TwentyPercent::class.java

        // execution
        val response = FifteenPercent.next::class.java

        // validation
        Assertions.assertTrue { expectedNext == response }
    }

    @Test
    fun `it should return expected percentage when asked to get percentage used to calculate cash back`() {
        // scenario
        val expectedPercentage = BigDecimal.valueOf(0.15)

        // execution
        val response = FifteenPercent.percentage

        // validation
        Assertions.assertTrue { expectedPercentage == response }
    }

    @Test
    fun `it should calculate cash back when asked for do it`() {
        // scenario
        val value = randomValueUntil(BigDecimal.valueOf(1500))
        val percentage = BigDecimal.valueOf(0.15)
        val expectedCashBack = value.multiply(percentage).scaled()

        // execution
        val (_, cashBack) = FifteenPercent.calculateCashBackValue(value)

        // validation
        Assertions.assertTrue { expectedCashBack == cashBack }
    }

    @Test
    fun `it should calculate with twenty percent of cash back when the value is more than 1500`() {
        // scenario
        val value = generateRandomBigDecimalFromRange(BigDecimal.valueOf(1501), BigDecimal.valueOf(2000))
        val expectedPercentage = BigDecimal.valueOf(0.20)

        // execution
        val (percentage, _) = FifteenPercent.calculateCashBackValue(value)

        // validation
        Assertions.assertEquals(expectedPercentage, percentage)
    }

}