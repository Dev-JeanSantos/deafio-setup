package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaTransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.ContaResponse
import com.example.desafiosetup.adapter.web.v1.response.MenssagemGenericaResponse
import com.example.desafiosetup.adapter.web.v1.response.TransferenciaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import java.math.BigDecimal

interface ContaUseCase {
    fun transferir(transferenciaRequest:TransferenciaRequest):MenssagemGenericaResponse
    fun processar(valor: BigDecimal, debito: String, credito:String)
    fun confirmarTransferencia(correntistaTransferenciaRequest: CorrentistaTransferenciaRequest): MenssagemGenericaResponse
    fun processarTransferencia(transferencia: Transferencia)
}