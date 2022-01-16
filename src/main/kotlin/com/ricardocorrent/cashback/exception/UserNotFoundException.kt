package com.ricardocorrent.cashback.exception

class UserNotFoundException(cpf: String) : ClientException(
    "The provided user was not found using $cpf",
    null,
)
