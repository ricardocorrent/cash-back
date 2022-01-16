package com.ricardocorrent.cashback.exception

class EmailAlreadyUsedException : ClientException(
    "The email provided is already in use",
    null,
)
