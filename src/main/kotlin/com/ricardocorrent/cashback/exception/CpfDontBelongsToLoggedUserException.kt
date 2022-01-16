package com.ricardocorrent.cashback.exception

class CpfDontBelongsToLoggedUserException : ClientException(
    "The provided cpf don't belongs to logged user",
    null,
)
