package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.input.SalvarCorrentistaUseCase
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Service

@Service
class CorrentistaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,

    ) : SalvarCorrentistaUseCase{

    override fun salvarCorrentista(correntistaRequest: CorrentistaRequest) {
        val correntista = Correntista(correntistaRequest.nome, "300")
        correntistaRepositorioPorta.salvar(correntista)
    }

    override fun buscar(numeroConta: String): Correntista {
       try {
           return correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(numeroConta)
       }catch (e: NegocioException){
           throw e
       }
    }
}