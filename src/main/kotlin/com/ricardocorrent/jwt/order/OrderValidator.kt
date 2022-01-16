package com.ricardocorrent.jwt.order

import org.springframework.stereotype.Component

@Component
class OrderValidator(
    private val repository: OrderRepository,
) {
}