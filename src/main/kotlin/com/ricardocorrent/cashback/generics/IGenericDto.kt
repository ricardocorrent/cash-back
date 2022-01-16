package com.ricardocorrent.cashback.generics

import java.io.Serializable

interface IGenericDto<U> : Serializable {
    var id: U?
}
