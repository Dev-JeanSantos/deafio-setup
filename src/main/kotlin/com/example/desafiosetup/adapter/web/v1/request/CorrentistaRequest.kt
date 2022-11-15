package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CorrentistaRequest(
    @JsonProperty("nome")
    val nome: String,
    @JsonProperty("valor")
    val valor: BigDecimal
)
