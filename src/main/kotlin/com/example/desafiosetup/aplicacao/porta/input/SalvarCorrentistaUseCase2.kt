package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest

interface SalvarCorrentistaUseCase2 {
    fun salvarCorrentista(correntistaRequest: CorrentistaRequest)
}