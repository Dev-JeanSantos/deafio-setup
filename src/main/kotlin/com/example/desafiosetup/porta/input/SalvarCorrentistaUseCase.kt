package com.example.desafiosetup.porta.input

import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse

interface SalvarCorrentistaUseCase {
    fun salvarCorrentista(nome: String): CorrentistaResponse
}