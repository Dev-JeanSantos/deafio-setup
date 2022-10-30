package com.example.desafiosetup.adapter.web.v1.controller

import com.example.desafiosetup.adapter.web.v1.api.CorrentistaApi2
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista2
import com.example.desafiosetup.aplicacao.porta.input.SalvarCorrentistaUseCase
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class CorrentistaApiController2(
    @Autowired
    private val correntistaRepositorioPorta2: CorrentistaRepositorioPorta2,
    private val salvarCorrentistaUseCase: SalvarCorrentistaUseCase
):CorrentistaApi2{

    override fun salvarCliente(correntistaRequest: CorrentistaRequest){

        val correntista =  Correntista2(correntistaRequest.nome, "100")
        correntistaRepositorioPorta2.salvar(correntista)
    }

}