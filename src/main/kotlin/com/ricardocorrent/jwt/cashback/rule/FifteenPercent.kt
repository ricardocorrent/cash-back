package com.ricardocorrent.jwt.cashback.rule

import com.ricardocorrent.jwt.generics.ICashBack
import com.ricardocorrent.jwt.utils.scaled
import java.math.BigDecimal

object FifteenPercent : ICashBack {

    override val next: ICashBack
        get() = TwentyPercent

    override val percentage: BigDecimal
        get() = BigDecimal.valueOf(0.15)

    override fun calculateCashBackValue(value: BigDecimal) =
        value.takeIf { it <= BigDecimal.valueOf(1500) }
            ?.let { percentage to it.multiply(percentage).scaled() }
            ?: next.calculateCashBackValue(value)

}
