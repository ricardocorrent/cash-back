package com.ricardocorrent.jwt.order.converter

import com.ricardocorrent.jwt.order.Order
import com.ricardocorrent.jwt.order.dto.OrderRequestDto
import com.ricardocorrent.jwt.order.dto.OrderResponseDto
import org.mapstruct.*

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface OrderConverter {

    @Mappings(
        value = [
            Mapping(source = "cpf", target = "user.cpf"),
            Mapping(target = "status", ignore = true),
        ]
    )
    fun toEntity(source: OrderRequestDto): Order


    @Named("orderToOrderResponseDto")
    fun toDto(source: Order): OrderResponseDto


    @IterableMapping(qualifiedByName = ["orderToOrderResponseDto"])
    @Named("ordersToOrdersResponseDto")
    fun toDto(source: List<Order>): List<OrderResponseDto>

}