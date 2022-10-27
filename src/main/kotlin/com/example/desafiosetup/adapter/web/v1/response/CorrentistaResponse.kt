package com.example.desafiosetup.adapter.web.v1.response

import java.math.BigDecimal

data class CorrentistaResponse(
    val nome: String,
    val numeroConta: String,
    val saldo: BigDecimal
)
