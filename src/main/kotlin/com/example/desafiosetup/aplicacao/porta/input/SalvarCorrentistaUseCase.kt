package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest

interface SalvarCorrentistaUseCase {
    fun salvarCorrentista(correntistaRequest: CorrentistaRequest)
}