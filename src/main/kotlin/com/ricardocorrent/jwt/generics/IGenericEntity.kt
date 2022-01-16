package com.ricardocorrent.jwt.generics

import java.io.Serializable

interface IGenericEntity<U> : Serializable {
    var id: U?
}
