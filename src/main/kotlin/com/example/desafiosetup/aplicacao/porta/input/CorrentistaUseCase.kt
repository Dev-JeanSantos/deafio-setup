package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import java.math.BigDecimal

interface CorrentistaUseCase {
    fun salvarCorrentista(correntistaRequest: CorrentistaRequest): CorrentistaResponse

    fun buscar(numeroConta: String): CorrentistaModel
    fun transferir(contaCredito: String, contaDebito: String, valor: BigDecimal): CorrentistaModel
}