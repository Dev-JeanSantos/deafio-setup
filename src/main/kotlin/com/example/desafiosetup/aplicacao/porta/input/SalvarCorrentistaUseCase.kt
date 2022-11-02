package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

interface SalvarCorrentistaUseCase {
    fun salvarCorrentista(correntistaRequest: CorrentistaRequest)

    fun buscar(numeroConta: String): Correntista
}