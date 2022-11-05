package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro
import com.example.desafiosetup.aplicacao.porta.input.ContaUseCase
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ContaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,
    @Autowired
    private val transferenciaService: TransferenciaService
): ContaUseCase {
    override fun transferir(transferenciaRequest: TransferenciaRequest): CorrentistaResponse {
        val contaDebito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaDebito)
        if (contaDebito.conta.saldo >= transferenciaRequest.valor){
            val contaCredito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaCredito)
            val debitoSucesso = processar(transferenciaRequest.valor, contaDebito.toConta(),contaCredito.toConta())
            return contaCredito.toResponse()
        }
        println("não entrou!")
        return  contaDebito.toResponse()
    }

}

fun processar(valor: BigDecimal?, debito: Conta?, credito: Conta?){

    if (Objects.isNull(valor)){
        Erro.obrigatorio("Valor da Transferência ")
    }
    if (Objects.isNull(debito)){
        Erro.obrigatorio("Conta Débito ")
    }
    if (Objects.isNull(credito)){
        Erro.obrigatorio("Conta Crédito ")
    }

    debito?.debitar(valor)
    credito?.creditar(valor)
}