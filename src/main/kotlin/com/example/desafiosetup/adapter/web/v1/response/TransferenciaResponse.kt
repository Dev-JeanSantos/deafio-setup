package com.example.desafiosetup.adapter.web.v1.response

import com.example.desafiosetup.aplicacao.dominio.constante.Status
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TransferenciaResponse(
    val nome: String,
    @JsonProperty("numero_conta")
    val numeroConta: String,
    @JsonProperty("id_correntista")
    val idCorrentista: String,
    val saldo: BigDecimal,
    @JsonProperty("status_transferencia")
    val status: Status
)