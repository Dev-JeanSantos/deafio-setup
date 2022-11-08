package com.example.desafiosetup.adapter.web.v1.response

import com.example.desafiosetup.aplicacao.dominio.modelo.Status
import com.fasterxml.jackson.annotation.JsonProperty

data class ContaResponse(
    val nome: String,
    @JsonProperty("numero_conta")
    val numeroConta: String,
    @JsonProperty("id_correntista")
    val idCorrentista: String,
    @JsonProperty("status_transferencia")
    val status: Status
)
