package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CorrentistaTransferenciaRequest(
    @JsonProperty("conta_transferencia")
    val contaTransferencia: String
)
