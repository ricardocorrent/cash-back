package com.ricardocorrent.cashback.user

import com.ricardocorrent.cashback.utils.justNumbers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun create(user: User): User {
        user.id = UUID.randomUUID()
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.cpf = user.cpf.justNumbers()

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
