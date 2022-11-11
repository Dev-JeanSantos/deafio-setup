package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.output.sns.DataLakePublisher
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaTransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.MenssagemGenericaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import com.example.desafiosetup.aplicacao.dominio.modelo.toModel
import com.example.desafiosetup.aplicacao.porta.input.ContaUseCase
import com.example.desafiosetup.aplicacao.porta.input.TransferenciaPorta
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ContaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,
    private val transferenciaPorta: TransferenciaPorta,
    private val datalakePublisher: DataLakePublisher
) : ContaUseCase {
    override fun transferir(transferenciaRequest: TransferenciaRequest): MenssagemGenericaResponse {
        val contaDebito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaDebito)

        if (contaDebito.conta.saldo >= transferenciaRequest.valor) {
            val contaCredito =
                correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaCredito)
            val transferencia = Transferencia(transferenciaRequest.valor, contaDebito.pk, contaCredito.pk)
            val debitoSucesso = processarTransferencia(transferencia)

            return MenssagemGenericaResponse("Transferencia sendo processada")
        }
        return MenssagemGenericaResponse("Saldo Insuficiente!")
    }

    override fun processar(
        valor: BigDecimal,
        debito: String,
        credito: String
    ){
        val debito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(debito)
        val credito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(credito)

        val debitoConta = debito.toConta()
        val creditoConta = credito.toConta()

        // Utiliza os metodos de creditar e debitar da classe criada
        debitoConta.debitar(valor)
        creditoConta.creditar(valor)

        // Cria copias dos registros existentes no banco e sobreescreve os saldos
        val debitoNovoSaldo = debito.copy(conta = debitoConta.toModel())
        val creditoNovoSaldo = credito.copy(conta = creditoConta.toModel())

        correntistaRepositorioPorta.transferirValoresEntreContas(debitoNovoSaldo, creditoNovoSaldo)
            .toContaResponse()
    }

    override fun confirmarTransferencia(correntistaTransferenciaRequest: CorrentistaTransferenciaRequest): MenssagemGenericaResponse {

        val valor = transferenciaPorta.confirmarTransferencia(correntistaTransferenciaRequest.contaDebito, correntistaTransferenciaRequest.contaCredito, correntistaTransferenciaRequest.reciboS3)
        processar(valor, correntistaTransferenciaRequest.contaDebito, correntistaTransferenciaRequest.contaCredito)
        return  MenssagemGenericaResponse("Transferencia confirmada com sucesso")
    }

    override fun processarTransferencia(transferencia: Transferencia) {
        transferenciaPorta.salvarTransferencia(transferencia)
        datalakePublisher.envioTransferencia(transferencia)
    }
}