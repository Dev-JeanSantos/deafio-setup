package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaTransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.adapter.web.v1.response.TransferenciaResponse
import java.math.BigDecimal

interface ContaUseCase {
    fun transferir(transferenciaRequest:TransferenciaRequest):TransferenciaResponse

    fun processar(valor: BigDecimal, debito: CorrentistaModel, credito: CorrentistaModel): TransferenciaResponse
    fun confirmarTransferencia(correntistaTransferenciaRequest: CorrentistaTransferenciaRequest): TransferenciaResponse

}