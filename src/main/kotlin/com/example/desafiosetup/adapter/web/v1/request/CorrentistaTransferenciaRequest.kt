package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
data class CorrentistaTransferenciaRequest(
    @JsonProperty("transferencia_id")
    val transferenciaId: String,
    @JsonProperty("recibo_s3")
    val reciboS3: String
)
