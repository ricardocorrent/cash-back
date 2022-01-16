package com.ricardocorrent.jwt.generics

import java.io.Serializable

interface IGenericDto<U> : Serializable {
    var id: U?
}
