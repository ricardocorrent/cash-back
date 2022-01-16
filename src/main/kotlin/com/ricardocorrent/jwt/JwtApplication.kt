package com.ricardocorrent.jwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["com.ricardocorrent.jwt.user.UserRepository"])
@EnableFeignClients
class JwtApplication

fun main(args: Array<String>) {
	runApplication<JwtApplication>(*args)
}
