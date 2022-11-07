package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TransferenciaRequest(
    @JsonProperty("conta_credito")
    val contaCredito: String,
    @JsonProperty("conta_debito")
    val contaDebito: String,
    @JsonProperty("valor")
    val valor: BigDecimal,
)
