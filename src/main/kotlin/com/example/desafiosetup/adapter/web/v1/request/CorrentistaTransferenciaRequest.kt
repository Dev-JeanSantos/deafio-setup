package com.example.desafiosetup.adapter.web.v1.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
@JsonIgnoreProperties(ignoreUnknown = true)
data class CorrentistaTransferenciaRequest(
    var transferenciaId: String,
    var reciboS3: String
)
