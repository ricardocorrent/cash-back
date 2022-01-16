package com.ricardocorrent.cashback.user

import com.ricardocorrent.cashback.user.dto.LoginVerifyDto
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("/verify")
    fun verify(auth: Authentication) =
        ResponseEntity.ok(
            LoginVerifyDto(
                userName = auth.name,
                logged = auth.isAuthenticated,
            )
        )

}
