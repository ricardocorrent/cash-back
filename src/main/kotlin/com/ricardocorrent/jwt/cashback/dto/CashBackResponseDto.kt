package com.ricardocorrent.jwt.cashback.dto

import java.math.BigDecimal

class CashBackResponseDto(
    var cashBackPercentage: BigDecimal,
    var cashBackValue: BigDecimal,
)
