package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.output.sns.DataLakePublisher
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaTransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.MenssagemGenericaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
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
        correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaDebito)?.let {
            if (it.conta.saldo >= transferenciaRequest.valor) {
                val contaCredito =
                    correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaCredito)
                if (contaCredito != null) {
                    processarTransferencia(transferenciaRequest.valor, it.pk, contaCredito.pk)
                }else{
                    return MenssagemGenericaResponse("Conta Credito não encontrada")
                }
                return MenssagemGenericaResponse("Transferencia sendo processada")
            }
        }

        return MenssagemGenericaResponse("Saldo Insuficiente!")
    }

    override fun processar(
        valor: BigDecimal,
        debito: String,
        credito: String
    ) {
        val debitoModel = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(debito)
        val creditoModel = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(credito)

        if (creditoModel != null && debitoModel != null) {
            try {
                val debitoConta = debitoModel.toConta()
                val creditoConta = creditoModel.toConta()

                debitoConta.debitar(valor)
                creditoConta.creditar(valor)

                val debitoNovoSaldo = debitoModel.copy(conta = debitoConta.toModel())
                val creditoNovoSaldo = creditoModel.copy(conta = creditoConta.toModel())

                correntistaRepositorioPorta.transferirValoresEntreContas(debitoNovoSaldo, creditoNovoSaldo)
                    .toContaResponse()
            } catch (ex: Exception) {
                throw NegocioException(mensagem = "Uma das contas não existe!")
            }
        }
    }

    override fun confirmarTransferencia(correntistaTransferenciaRequest: CorrentistaTransferenciaRequest): MenssagemGenericaResponse {

        try {
            val transferencia = transferenciaPorta.confirmarTransferencia(
                    correntistaTransferenciaRequest.transferenciaId,
                    correntistaTransferenciaRequest.reciboS3
            )
            processar(transferencia.valor, transferencia.contaDebito, transferencia.contaCredito)
            return MenssagemGenericaResponse("Transferencia confirmada com sucesso")
        } catch (ex: Exception) {
            MenssagemGenericaResponse("Deu bo")
            throw ex
        }
    }

    override fun processarTransferencia(valor: BigDecimal, contaDebito: String, contaCredito: String) {
        val transferencia = Transferencia(valor, contaDebito, contaCredito, transferenciaId = "")
        val transferenciaId = transferenciaPorta.salvarTransferencia(transferencia)
        transferencia.transferenciaId = transferenciaId
        datalakePublisher.envioTransferencia(transferencia)
    }
}