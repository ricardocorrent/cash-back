package com.ricardocorrent.jwt.order

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : MongoRepository<Order, UUID> {
    fun findAllByUserIdOrderByDateDesc(userId: UUID): List<Order>?
}