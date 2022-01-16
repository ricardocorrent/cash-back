package com.ricardocorrent.cashback.order

import com.ricardocorrent.cashback.order.converter.OrderConverter
import com.ricardocorrent.cashback.order.dto.OrderRequestDto
import com.ricardocorrent.cashback.order.dto.OrderResponseDto
import com.ricardocorrent.cashback.user.UserDetailsImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/order")
class OrderController(
    private val service: OrderService,
    private val converter: OrderConverter,
) {

    @PostMapping
    fun save(@Valid @RequestBody payload: OrderRequestDto): ResponseEntity<OrderResponseDto> {
        val response = service.save(converter.toEntity(payload))
        return ResponseEntity.ok(converter.toDto(response))
    }

    @GetMapping
    fun findAll(@AuthenticationPrincipal user: UserDetailsImpl): ResponseEntity<List<OrderResponseDto>> {
        val response = service.findAll(user)
        return ResponseEntity.ok(converter.toDto(response))
    }

}
