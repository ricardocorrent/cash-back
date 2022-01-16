package com.ricardocorrent.cashback.order.converter

import com.ricardocorrent.cashback.order.Order
import com.ricardocorrent.cashback.order.dto.OrderRequestDto
import com.ricardocorrent.cashback.order.dto.OrderResponseDto
import org.mapstruct.*

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface OrderConverter {

    @Mappings(
        value = [
            Mapping(source = "cpf", target = "user.cpf"),
            Mapping(target = "status", ignore = true),
            Mapping(target = "cashBack", ignore = true),
        ]
    )
    fun toEntity(source: OrderRequestDto): Order


    @Mappings(
        value = [
            Mapping(source = "cashBack.cashBackPercentage", target = "cashBackPercentage"),
            Mapping(source = "cashBack.cashBackValue", target = "cashBackValue"),
        ]
    )
    @Named("orderToOrderResponseDto")
    fun toDto(source: Order): OrderResponseDto


    @IterableMapping(qualifiedByName = ["orderToOrderResponseDto"])
    @Named("ordersToOrdersResponseDto")
    fun toDto(source: List<Order>): List<OrderResponseDto>

}