package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.sns.DataLakePublisher
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaTransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.ContaResponse
import com.example.desafiosetup.adapter.web.v1.response.TransferenciaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.toModel
import com.example.desafiosetup.aplicacao.porta.input.ContaUseCase
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ContaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,
    private val datalakePublisher: DataLakePublisher
) : ContaUseCase {
    override fun transferir(transferenciaRequest: TransferenciaRequest): ContaResponse {
        val contaDebito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaDebito)
        if (contaDebito.conta.saldo >= transferenciaRequest.valor) {
            val contaCredito =
                correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaCredito)
            val debitoSucesso = processar(transferenciaRequest.valor, contaDebito, contaCredito)

            return debitoSucesso
        }
        println("não entrou!")
        return contaDebito.toContaResponse()
    }

    override fun processar(
        valor: BigDecimal,
        debito: CorrentistaModel,
        credito: CorrentistaModel
    ): ContaResponse {
        val debitoConta = debito.toConta()
        val creditoConta = credito.toConta()

        // Utiliza os metodos de creditar e debitar da classe criada
        debitoConta.debitar(valor)
        creditoConta.creditar(valor)

        // Cria copias dos registros existentes no banco e sobreescreve os saldos
        val debitoNovoSaldo = debito.copy(conta = debitoConta.toModel())
        val creditoNovoSaldo = credito.copy(conta = creditoConta.toModel())

        datalakePublisher.envioTransferencia(creditoNovoSaldo.toModel())

        return correntistaRepositorioPorta.transferirValoresEntreContas(debitoNovoSaldo, creditoNovoSaldo)
            .toContaResponse()
    }

    override fun confirmarTransferencia(correntistaTransferenciaRequest: CorrentistaTransferenciaRequest): TransferenciaResponse {
        val contaConfirmadaTransferencia = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(correntistaTransferenciaRequest.contaTransferencia)
        val contaAtualizada = contaConfirmadaTransferencia.update(contaConfirmadaTransferencia)

        return correntistaRepositorioPorta.confirmarTransferencia(contaAtualizada)
    }
}