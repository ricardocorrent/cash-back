package com.ricardocorrent.jwt.user

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users", schema = "public")
data class User(

    @Id
    @JsonIgnore
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "full_name")
    var fullName: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @Column(name = "cpf")
    var cpf: String,

    )
