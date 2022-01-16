package com.ricardocorrent.cashback.user

import com.ricardocorrent.cashback.utils.randomNumbersAsString
import com.ricardocorrent.cashback.utils.randomString
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

internal class UserServiceUnitTests {

    private lateinit var service: UserService
    private val userRepository: UserRepository = mockk()
    private val bCryptPasswordEncoder: BCryptPasswordEncoder = mockk()

    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        service = UserService(userRepository, bCryptPasswordEncoder)
        user = mockUser()

        every { bCryptPasswordEncoder.encode(any()) } returns randomString
        every { userRepository.save(any()) } returns user
        every { userRepository.findByEmail(any()) } returns null
    }

    @Test
    fun `when asked for create an user, user repository should be called`() {
        // execution
        service.create(user)

        // validation
        verify { userRepository.save(any()) }
    }

    private fun mockUser() = User(
        password = randomString,
        cpf = randomNumbersAsString,
        email = randomString,
    )
}