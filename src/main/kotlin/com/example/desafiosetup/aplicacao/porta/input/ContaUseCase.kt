package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse

interface ContaUseCase {
    fun transferir(transferenciaRequest:TransferenciaRequest):CorrentistaResponse
}