package com.ricardocorrent.jwt.cashback.rule

import com.ricardocorrent.jwt.generics.ICashBack
import com.ricardocorrent.jwt.utils.scaled
import java.math.BigDecimal

object TenPercent : ICashBack {

    override val next: ICashBack
        get() = FifteenPercent

    override val percentage: BigDecimal
        get() = BigDecimal.valueOf(0.10)

    override fun calculateCashBackValue(value: BigDecimal) =
        value.takeIf { it <= BigDecimal.valueOf(1000) }
            ?.let { percentage to it.multiply(FifteenPercent.percentage).scaled() }
            ?: next.calculateCashBackValue(value)

}
