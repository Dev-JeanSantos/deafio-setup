package com.example.desafiosetup.adapter.web.v1.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CorrentistaResponse(
    val nome: String,
    @JsonProperty("numero_conta")
    val numeroConta: String,
    @JsonProperty("id_correntista")
    val idCorrentista: String,
    val saldo: BigDecimal
)