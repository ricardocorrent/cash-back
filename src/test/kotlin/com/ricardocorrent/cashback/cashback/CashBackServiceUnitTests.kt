package com.ricardocorrent.cashback.cashback

import com.ricardocorrent.cashback.cashback.client.CashBackResumeClient
import com.ricardocorrent.cashback.utils.randomString
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CashBackServiceUnitTests {

    private lateinit var service: CashBackService
    private val client: CashBackResumeClient = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        service = CashBackService(client)
    }

    @Test
    fun `it should call client when asked for get resume`() {
        // execution
        service.getResume(randomString)

        // validation
        verify { client.getResume(any(), any()) }
    }
}