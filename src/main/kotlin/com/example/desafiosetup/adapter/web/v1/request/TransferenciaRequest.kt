package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TransferenciaRequest(
    @JsonProperty("contaCredito")
    val contaCredito: String,
    @JsonProperty("contaDebito")
    val contaDebito: String,
    @JsonProperty("valor")
    val valor: BigDecimal,
)
