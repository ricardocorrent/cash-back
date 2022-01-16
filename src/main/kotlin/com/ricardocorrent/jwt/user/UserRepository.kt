package com.ricardocorrent.jwt.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String?): User?
    fun findByCpf(cpf: String): User?
}
