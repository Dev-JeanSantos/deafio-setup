package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CorrentistaRequest(
    @JsonProperty("nome")
    val nome: String
)
