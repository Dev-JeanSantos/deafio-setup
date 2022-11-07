package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.sns.DataLakePublisher
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro
import com.example.desafiosetup.aplicacao.dominio.modelo.toModel
import com.example.desafiosetup.aplicacao.porta.input.ContaUseCase
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ContaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,
    private val datalakePublisher: DataLakePublisher
) : ContaUseCase {
    override fun transferir(transferenciaRequest: TransferenciaRequest): CorrentistaResponse {
        val contaDebito = correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaDebito)
        if (contaDebito.conta.saldo >= transferenciaRequest.valor) {
            val contaCredito =
                correntistaRepositorioPorta.buscarCorrentistaPorNumeroConta(transferenciaRequest.contaCredito)
            val debitoSucesso = processar(transferenciaRequest.valor, contaDebito, contaCredito)

            return debitoSucesso
        }
        println("não entrou!")
        return contaDebito.toResponse()
    }

    override fun processar(
        valor: BigDecimal,
        debito: CorrentistaModel,
        credito: CorrentistaModel
    ): CorrentistaResponse {
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
            .toResponse()
    }
}