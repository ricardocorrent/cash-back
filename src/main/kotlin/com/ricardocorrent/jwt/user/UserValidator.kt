package com.ricardocorrent.jwt.user

import org.springframework.stereotype.Component

@Component
class UserValidator(
    private val repository: UserRepository,
) {
    fun findUserByEmailOrNull(email: String) =
        repository.findByEmail(email)
}