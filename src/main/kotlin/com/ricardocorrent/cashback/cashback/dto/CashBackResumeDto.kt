package com.ricardocorrent.cashback.cashback.dto

import java.io.Serializable
import java.math.BigDecimal

class CashBackResumeDto : Serializable {
    val statusCode: Int? = null
    val body: Body? = null
}

class Body {
    val credit: BigDecimal? = null
}
