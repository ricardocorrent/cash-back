package com.ricardocorrent.jwt.cashback.rule

import com.ricardocorrent.jwt.generics.ICashBack
import com.ricardocorrent.jwt.utils.scaled
import java.math.BigDecimal

object TwentyPercent : ICashBack {

    override val next: ICashBack?
        get() = null

    override val percentage: BigDecimal
        get() = BigDecimal.valueOf(0.20)

    override fun calculateCashBackValue(value: BigDecimal) =
        percentage to value.multiply(percentage).scaled()

}
