package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CorrentistaTransferenciaRequest(
    @JsonProperty("conta_debito")
    val contaDebito: String,
    @JsonProperty("conta_credito")
    val contaCredito: String,
    @JsonProperty("recibo_s3")
    val reciboS3: String
)
