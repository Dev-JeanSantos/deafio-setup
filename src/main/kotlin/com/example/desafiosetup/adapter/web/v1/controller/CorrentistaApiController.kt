package com.example.desafiosetup.adapter.web.v1.controller

import com.example.desafiosetup.adapter.web.v1.api.CorrentistaApi
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.porta.input.CorrentistaUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class CorrentistaApiController(
    @Autowired
    private val correntistaUseCase: CorrentistaUseCase,
):CorrentistaApi{

    override fun salvarConta(correntistaRequest: CorrentistaRequest): CorrentistaResponse{
        return correntistaUseCase.salvarCorrentista(correntistaRequest)
    }

    override fun buscarCorrentista(numeroConta: String): CorrentistaResponse {
       return correntistaUseCase.buscar(numeroConta).toResponse()
    }
}