package com.ricardocorrent.jwt.user

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, UUID> {
    fun findByEmail(email: String?): User?
    fun findByCpf(cpf: String): User?
}
