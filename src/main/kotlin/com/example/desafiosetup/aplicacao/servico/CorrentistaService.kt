package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.converter.toResponse
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.input.CorrentistaUseCase
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Service

@Service
class CorrentistaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta
) : CorrentistaUseCase {

    override fun salvarCorrentista(clienteRequest: CorrentistaRequest): CorrentistaResponse {
        val correntista = Correntista(clienteRequest.nome)
        val resposta = correntistaRepositorioPorta.salvar(correntista).toResponse()
        return resposta
    }

    override fun buscar(numeroConta: String): CorrentistaModel? {
        return correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(numeroConta)
    }
}