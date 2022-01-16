package com.ricardocorrent.jwt.order

import com.ricardocorrent.jwt.cashback.CashBack
import com.ricardocorrent.jwt.exception.UserNotFoundException
import com.ricardocorrent.jwt.user.User
import com.ricardocorrent.jwt.user.UserDetailsImpl
import com.ricardocorrent.jwt.user.UserRepository
import com.ricardocorrent.jwt.utils.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
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
    fun `when the cpf is not allowed to approved, it should set status as VALIDATION`() {
        // scenario
        val expectedStatus = OrderStatusEnum.VALIDATION
        val mockedOrder = mockOrder.copy(status = null)

        every { repository.save(mockedOrder) } returns mockedOrder

        // execution
        val response = service.save(mockedOrder)

        // validation
        assertEquals(expectedStatus, response.status)
    }

    @Test
    fun `when the cpf is allowed to approved, it should set status as APPROVED`() {
        // scenario
        val expectedStatus = OrderStatusEnum.APPROVED
        val mockedUser = User(cpf = "153.509.460-56")
        val mockedOrder = mockOrder.copy(
            status = null,
            user = mockedUser,
        )

        every { userRepository.findByCpf(mockedOrder.getUserCpf()) } returns mockedOrder.user
        every { repository.save(mockedOrder) } returns mockedOrder

        // execution
        val response = service.save(mockedOrder)

        // validation
        assertEquals(expectedStatus, response.status)
    }

    @Test
    fun `when the value is less than 1000 the cash back percentage should be 10 percent`() {
        // scenario
        val expectedCashBackPercentage = BigDecimal.valueOf(0.10).formatDecimal()
        val mockedOrder = mockOrder.copy(
            value = BigDecimal.valueOf(1000.00),
            cashBack = null,
        )

        every { repository.save(mockedOrder) } returns mockedOrder

        // execution
        val response = service.save(mockedOrder)

        // validation
        assertEquals(expectedCashBackPercentage, response.cashBack!!.cashBackPercentage)
    }

    @Test
    fun `when the value is less than 1000 the cash back value should be calculate using 10 percent`() {
        // scenario
        val expectedCashBackPercentage = BigDecimal.valueOf(0.10)
        val expectedValue = BigDecimal.valueOf(854.40)
        val expectedCashBackValue = expectedValue.multiply(expectedCashBackPercentage).scaled()

        val mockedOrder = mockOrder.copy(
            value = expectedValue,
            cashBack = null,
        )

        every { repository.save(mockedOrder) } returns mockedOrder

        // execution
        val response = service.save(mockedOrder)

        // validation
        assertEquals(expectedCashBackValue, response.cashBack!!.cashBackValue)
    }

    @Test
    fun `when the provided user is not found, an error should be thrown`() {
        // scenario
        val userCpf = mockOrder.getUserCpf()
        every { userRepository.findByCpf(userCpf) } throws UserNotFoundException(userCpf)

        // validation
        assertThrows<UserNotFoundException> {
            // execution
            service.save(mockOrder)
        }
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
