package com.ricardocorrent.cashback.user.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UserRequestDto(

    @NotNull
    @NotEmpty
    val fullName: String,

    @NotNull
    @Email
    val email: String,

    @NotNull
    @NotEmpty
    val password: String,

    @NotNull
    @NotEmpty
    val cpf: String,

)
