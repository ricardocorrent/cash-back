package com.ricardocorrent.cashback.cashback

import com.ricardocorrent.cashback.cashback.client.CashBackResumeClient
import com.ricardocorrent.cashback.cashback.dto.CashBackResumeDto
import org.springframework.stereotype.Service

@Service
class CashBackService(
    private val client: CashBackResumeClient
) {

    fun getResume(cpf: String): CashBackResumeDto {
        return client.getResume(mapOf("token" to "\${external.cash.back.token}"), cpf)
    }

}
