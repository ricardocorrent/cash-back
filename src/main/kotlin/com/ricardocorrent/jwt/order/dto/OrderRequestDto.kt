package com.ricardocorrent.jwt.order.dto

import com.ricardocorrent.jwt.generics.IGenericDto
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotNull

class OrderRequestDto(

    override var id: UUID? = null,

    @NotNull
    val code: String,

    @NotNull
    val value: BigDecimal,

    @NotNull
    val date: LocalDateTime,

    @NotNull
    val cpf: String,

    ) : IGenericDto<UUID>
