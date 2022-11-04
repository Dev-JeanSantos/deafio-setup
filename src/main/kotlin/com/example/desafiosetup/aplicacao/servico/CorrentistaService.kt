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
import java.math.BigDecimal

@Service
class CorrentistaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,
    ) : CorrentistaUseCase{

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
    override fun transferir(contaCredito: String, contaDebito: String, valor: BigDecimal): CorrentistaModel {
        TODO("Not yet implemented")
//        try {
//            val possivelContaCredito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(contaCredito)
//            if (possivelContaCredito.conta.saldo >= valor){
//                val conDebito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(contaDebito)
//                val adicionarSaldo = conDebito.conta.saldo.add(valor)
//                val subtrairSaldo = possivelContaCredito.conta.saldo.subtract(valor)
//
//                correntistaRepositorioPorta.salvar(possivelContaCredito).toModel()
//                correntistaRepositorioPorta.salvar(conDebito).toModel()
//
//
//            }
//        }
    }
}