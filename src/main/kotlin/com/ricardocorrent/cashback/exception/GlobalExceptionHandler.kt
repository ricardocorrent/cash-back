package com.ricardocorrent.cashback.exception

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        var responseStatus = status

        val fieldName = runCatching { (ex.rootCause as MissingKotlinParameterException).parameter.name }
            .onSuccess { responseStatus = HttpStatus.UNPROCESSABLE_ENTITY }
            .getOrNull()

        val details = fieldName.takeIf { it != null }
            ?.let {
                mapOf(
                    "field" to it,
                    "message" to "There is an error at $it field, check your payload and try it again!",
                )
            }
            ?: null

        return handleExceptionInternal(ex, details, headers, responseStatus, request);
    }

}