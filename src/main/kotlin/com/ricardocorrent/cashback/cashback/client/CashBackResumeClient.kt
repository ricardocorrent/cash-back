package com.ricardocorrent.cashback.cashback.client

import com.ricardocorrent.cashback.cashback.dto.CashBackResumeDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    contextId = "CashBackId",
    value = "CashBackValue",
    url = "\${external.cash.back.host}",
)
interface CashBackResumeClient {

    @GetMapping(value = ["?cpf={cpf}"], consumes = ["application/json"])
    fun getResume(
        @RequestHeader headers: Map<String, String>,
        @PathVariable("cpf") cpf: String,
    ): CashBackResumeDto
}
