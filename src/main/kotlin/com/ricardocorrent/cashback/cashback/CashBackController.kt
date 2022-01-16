package com.ricardocorrent.cashback.cashback

import com.ricardocorrent.cashback.cashback.dto.CashBackResumeResponseDto
import org.slf4j.LoggerFactory
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

    private companion object {
        private val LOGGER = LoggerFactory.getLogger(CashBackController::class.java)
    }

    @GetMapping("/{cpf}")
    fun resume(@PathVariable cpf: String): ResponseEntity<CashBackResumeResponseDto> {
        val credit = runCatching { service.getResume(cpf).body!!.credit!! }
            .onFailure {
                LOGGER.info(
                    "An error occurs during the request. Check the class " +
                            "${CashBackController.javaClass.name} to see more details"
                )
            }
            .getOrThrow()

        return ResponseEntity.ok(CashBackResumeResponseDto(credit))
    }

}
