package com.ricardocorrent.jwt.cashback

import com.ricardocorrent.jwt.cashback.dto.CashBackResumeDto
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/cashback"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CashBackController(
    private val service: CashBackService,
) {

    @GetMapping("/{cpf}")
    fun resume(@PathVariable cpf: String): ResponseEntity<CashBackResumeDto> {
        return ResponseEntity.ok(service.getResume(cpf))
    }

}
