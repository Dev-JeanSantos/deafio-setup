package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista2
import com.example.desafiosetup.aplicacao.porta.input.SalvarCorrentistaUseCase2
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta2
import org.springframework.stereotype.Service

@Service
class CorrentistaService2(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta2,

) : SalvarCorrentistaUseCase2{

    override fun salvarCorrentista(correntistaRequest: CorrentistaRequest) {
        val correntista = Correntista2(correntistaRequest.nome, "100")
        correntistaRepositorioPorta.salvar(correntista)
    }
}