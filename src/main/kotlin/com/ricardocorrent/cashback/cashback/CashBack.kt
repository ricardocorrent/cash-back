package com.ricardocorrent.cashback.cashback

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CashBack(

    @Column(name = "cash_back_percentage")
    var cashBackPercentage: BigDecimal,

    @Column(name = "cash_back_value")
    var cashBackValue: BigDecimal,

)
