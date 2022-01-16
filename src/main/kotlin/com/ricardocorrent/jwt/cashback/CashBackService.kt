package com.ricardocorrent.jwt.cashback

import com.ricardocorrent.jwt.cashback.client.CashBackResumeClient
import com.ricardocorrent.jwt.cashback.dto.CashBackResumeDto
import org.springframework.stereotype.Service

@Service
class CashBackService(
    private val client: CashBackResumeClient
) {

    fun getResume(cpf: String): CashBackResumeDto {
        return client.getResume(mapOf("token" to "\${external.cash.back.token}"), cpf)
    }
}