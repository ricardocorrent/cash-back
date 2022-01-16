package com.ricardocorrent.cashback.generics

import java.math.BigDecimal

interface ICashBack {

    val next: ICashBack?

    val percentage: BigDecimal

    fun calculateCashBackValue(value: BigDecimal) : Pair<BigDecimal, BigDecimal>

}