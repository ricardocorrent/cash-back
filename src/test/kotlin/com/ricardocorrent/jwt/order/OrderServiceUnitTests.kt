package com.ricardocorrent.jwt.order

import com.ricardocorrent.jwt.cashback.CashBack
import com.ricardocorrent.jwt.user.User
import com.ricardocorrent.jwt.user.UserDetailsImpl
import com.ricardocorrent.jwt.user.UserRepository
import com.ricardocorrent.jwt.utils.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class OrderServiceUnitTests {

    private lateinit var service: OrderService
    private val repository: OrderRepository = mockk(relaxed = true)
    private val userRepository: UserRepository = mockk(relaxed = true)

    private val order: Order = mockk(relaxed = true)
    private val user: User = mockk(relaxed = true)
    private val userDetails: UserDetailsImpl = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        service = OrderService(repository, userRepository)

        every { order.getUserCpf() } returns randomNumbersAsString
        every { repository.save(any()) } returns order
        every { userRepository.findByCpf(any()) } returns user
    }

    @Test
    fun `when an order is saved it should call repository save`() {
        // execution
        service.save(order)

        // validation
        verify { repository.save(any()) }
    }

    @Test
    fun `when the cpf is not allowed to approved, it should set status as validation`() {
        // scenario
        val expectedStatus = OrderStatusEnum.VALIDATION
        val mockedOrder = mockOrder.copy(status = null)

        every { repository.save(mockedOrder) } returns mockedOrder

        // execution
        val response = service.save(mockedOrder)

        // validation
        assertEquals(expectedStatus, response.status)
    }

    private val mockOrder: Order
        get() = Order(
            id = randomUUIDs,
            code = randomString,
            value = randomBigDecimalValue,
            date = LocalDateTime.now(),
            user = user,
            status = randomOrderStatus,
            cashBack = CashBack(
                cashBackPercentage = listOf(10, 15, 20).random().toBigDecimal(),
                cashBackValue = randomBigDecimalValue,
            ),
        )

}