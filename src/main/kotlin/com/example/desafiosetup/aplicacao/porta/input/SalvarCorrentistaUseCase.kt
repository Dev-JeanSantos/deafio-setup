package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse

interface SalvarCorrentistaUseCase {
    fun salvarCorrentista(correntistaRequest: CorrentistaRequest): CorrentistaResponse

    fun buscar(numeroConta: String): CorrentistaModel
}