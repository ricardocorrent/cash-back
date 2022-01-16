package com.ricardocorrent.jwt.exception

class UserNotFoundException(cpf: String) : ClientException(
    "The provided user was not found using $cpf",
    null,
)
