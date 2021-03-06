package com.ricardocorrent.cashback.security

import com.ricardocorrent.cashback.user.UserService
import com.ricardocorrent.cashback.user.converter.UserConverter
import com.ricardocorrent.cashback.user.dto.UserRequestDto
import com.ricardocorrent.cashback.user.dto.UserResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/signup")
class SignupController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var converter: UserConverter

    @PostMapping
    fun signup(@RequestBody payload: UserRequestDto): ResponseEntity<UserResponseDto> {
        val userCreated = userService.create(converter.toEntity(payload))

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(converter.toDto(userCreated))
    }

}
