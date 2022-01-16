package com.ricardocorrent.jwt.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class User(

    @Id
    var id: UUID? = null,

    var fullName: String? = null,

    var email: String? = null,

    var password: String? = null,

    var cpf: String,

    )
