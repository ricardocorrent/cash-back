package com.ricardocorrent.jwt.order

import com.ricardocorrent.jwt.generics.IGenericEntity
import com.ricardocorrent.jwt.user.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Document
data class Order(

    @Id
    override var id: UUID?,

    val code: String,

    val value: BigDecimal,

    val date: LocalDateTime,

    var user: User,

    var status: OrderStatusEnum? = OrderStatusEnum.VALIDATION,

    var cashBackPercentage: BigDecimal,

    var cashBackValue: BigDecimal,

    ) : IGenericEntity<UUID> {
    fun getUserCpf() = user.cpf
}