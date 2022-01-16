package com.ricardocorrent.jwt.order

import com.ricardocorrent.jwt.cashback.CashBack
import com.ricardocorrent.jwt.generics.IGenericEntity
import com.ricardocorrent.jwt.user.User
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