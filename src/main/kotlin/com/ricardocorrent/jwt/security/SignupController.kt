package com.ricardocorrent.jwt.security

import com.ricardocorrent.jwt.user.User
import com.ricardocorrent.jwt.user.UserService
import com.ricardocorrent.jwt.user.converter.UserConverter
import com.ricardocorrent.jwt.user.dto.UserResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/signup")
class SignupController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var converter: UserConverter

    @PostMapping
    fun signup(@RequestBody user: User): ResponseEntity<UserResponseDto> {
        val userCreated = userService.create(user)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(converter.toDto(userCreated))
    }

}
