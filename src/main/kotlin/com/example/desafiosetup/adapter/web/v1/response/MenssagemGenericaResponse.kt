package com.example.desafiosetup.adapter.web.v1.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MenssagemGenericaResponse(
    @JsonProperty("mensagem")
    val mensagem: String
)