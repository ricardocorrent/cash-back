package com.ricardocorrent.cashback.user

import com.ricardocorrent.cashback.exception.EmailAlreadyUsedException
import com.ricardocorrent.cashback.utils.justNumbers
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) {

    fun create(user: User): User {
        user.id = UUID.randomUUID()
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.cpf = user.cpf.justNumbers()

        userRepository.findByEmail(user.email).takeIf { it != null }
            ?.let { throw EmailAlreadyUsedException() }

        return userRepository.save(user)
    }

    fun myself(): String? {
        return userRepository.findByEmail(getCurrentUserEmail())?.fullName
    }

    private fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return user.username
    }

}
