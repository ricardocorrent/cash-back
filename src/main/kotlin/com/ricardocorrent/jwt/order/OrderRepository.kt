package com.ricardocorrent.jwt.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<Order, UUID> {
    fun findAllByUserIdOrderByDateDesc(userId: UUID) : List<Order>?
}