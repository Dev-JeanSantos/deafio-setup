package com.example.desafiosetup.adapter.web.v1.request

import java.math.BigDecimal

data class TransferenciaRequest(
    val contaCredito: String,
    val contaDebito: String,
    val valor: BigDecimal,
)
