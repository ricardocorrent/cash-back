package com.ricardocorrent.jwt.cashback.dto

import java.io.Serializable
import java.math.BigDecimal

class CashBackResumeResponseDto(
    val credit: BigDecimal,
) : Serializable