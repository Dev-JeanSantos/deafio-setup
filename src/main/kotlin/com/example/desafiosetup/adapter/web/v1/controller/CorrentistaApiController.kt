package com.example.desafiosetup.adapter.web.v1.controller

import com.example.desafiosetup.adapter.web.v1.api.CorrentistaApi
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.aplicacao.porta.input.SalvarCorrentistaUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class CorrentistaApiController(
    @Autowired
    private val salvarCorrentistaUseCase: SalvarCorrentistaUseCase
):CorrentistaApi{

    override fun salvarCliente(correntistaRequest: CorrentistaRequest){
        return salvarCorrentistaUseCase.salvarCorrentista(correntistaRequest)
    }

}