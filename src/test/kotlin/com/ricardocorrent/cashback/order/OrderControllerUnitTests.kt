package com.ricardocorrent.cashback.order

import com.ricardocorrent.cashback.order.converter.OrderConverter
import com.ricardocorrent.cashback.order.dto.OrderRequestDto
import com.ricardocorrent.cashback.user.User
import com.ricardocorrent.cashback.user.UserDetailsImpl
import com.ricardocorrent.cashback.utils.randomBigDecimalValue
import com.ricardocorrent.cashback.utils.randomNumbersAsString
import com.ricardocorrent.cashback.utils.randomString
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class OrderControllerUnitTests {

    private lateinit var controller: OrderController
    private val service: OrderService = mockk(relaxed = true)
    private val converter: OrderConverter = mockk(relaxed = true)

    private lateinit var order: OrderRequestDto
    private lateinit var userDetails: UserDetailsImpl

    @BeforeEach
    fun setUp() {
        controller = OrderController(service, converter)
        order = mockOrderRequestDto()
        userDetails = mockUserDetailsImpl()
    }

    @Test
    fun `it should call service save method when asked for save an order`() {
        // execution
        controller.save(order)

        // validation
        verify { service.save(any()) }
    }

    @Test
    fun `it should call service find all method when asked for find all orders`() {
        // execution
        controller.findAll(userDetails)

        // validation
        verify { service.findAll(any()) }
    }

    private fun mockOrderRequestDto() = OrderRequestDto(
        code = randomString,
        value = randomBigDecimalValue,
        date = LocalDateTime.now(),
        cpf = randomNumbersAsString,
    )

    private fun mockUserDetailsImpl() = UserDetailsImpl(
        user = User(
            cpf = randomNumbersAsString
        )
    )

}
