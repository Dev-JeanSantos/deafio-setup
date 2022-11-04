package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
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

    override fun salvarCorrentista(correntistaRequest: CorrentistaRequest): CorrentistaResponse {
        val correntista = Correntista(correntistaRequest.nome)
        val resposta = correntistaRepositorioPorta.salvar(correntista)
        return CorrentistaResponse(
                nome = resposta.nome,
                numeroConta = resposta.conta.numero,
                saldo = resposta.conta.saldo,
                idCorrentista = resposta.idCorrentista
        )
    }

    override fun buscar(numeroConta: String): CorrentistaModel {
       try {
           val possivelCorrentista = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(numeroConta)
           return possivelCorrentista
       }catch (e: NegocioException){
           throw e
       }
    }
}