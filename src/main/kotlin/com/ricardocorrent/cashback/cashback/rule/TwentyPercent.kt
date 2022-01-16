package com.ricardocorrent.cashback.cashback.rule

import com.ricardocorrent.cashback.generics.ICashBack
import com.ricardocorrent.cashback.utils.scaled
import java.math.BigDecimal

object TwentyPercent : ICashBack {

    override val next: ICashBack?
        get() = null

    override val percentage: BigDecimal
        get() = BigDecimal.valueOf(0.20)

    override fun calculateCashBackValue(value: BigDecimal) =
        percentage to value.multiply(percentage).scaled()

}
