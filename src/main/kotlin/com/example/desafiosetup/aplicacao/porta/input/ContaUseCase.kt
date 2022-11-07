package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import java.math.BigDecimal

interface ContaUseCase {
    fun transferir(transferenciaRequest:TransferenciaRequest):CorrentistaResponse

    fun processar(valor: BigDecimal, debito: CorrentistaModel, credito: CorrentistaModel): CorrentistaResponse
}