package com.ricardocorrent.cashback.cashback

import com.ricardocorrent.cashback.utils.randomString
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CashBackControllerUnitTests {

    private lateinit var controller: CashBackController
    private val service: CashBackService = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        controller = CashBackController(service)
    }

    @Test
    fun `it should call service when asked for resume`() {
        // execution
        controller.resume(randomString)

        // validation
        verify { service.getResume(any()) }
    }
}