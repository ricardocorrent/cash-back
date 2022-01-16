package com.ricardocorrent.cashback.order

import com.ricardocorrent.cashback.cashback.CashBack
import com.ricardocorrent.cashback.generics.IGenericEntity
import com.ricardocorrent.cashback.user.User
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "order", schema = "public")
data class Order(

    @Id
    @Column(name = "id")
    override var id: UUID?,

    @Column(name = "code")
    val code: String,

    @Column(name = "value")
    val value: BigDecimal,

    @Column(name = "date")
    val date: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    var user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: OrderStatusEnum? = OrderStatusEnum.VALIDATION,

    @Embedded
    var cashBack: CashBack?,

    ) : IGenericEntity<UUID> {
    fun getUserCpf() = user.cpf
}