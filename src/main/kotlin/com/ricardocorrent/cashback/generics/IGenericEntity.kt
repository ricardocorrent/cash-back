package com.ricardocorrent.cashback.generics

import java.io.Serializable

interface IGenericEntity<U> : Serializable {
    var id: U?
}
