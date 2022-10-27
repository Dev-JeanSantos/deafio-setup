package com.example.desafiosetup.adapter.input

import com.example.desafiosetup.aplicacao.dominio.modelo.Erro
import com.example.desafiosetup.aplicacao.servico.Transferencia
import com.example.desafiosetup.porta.input.TransferenciaPorta
import com.example.desafiosetup.porta.output.ContaRepositorioPorta
import java.math.BigDecimal
import java.util.Objects.isNull
import javax.inject.Inject
import javax.inject.Named

@Named
class TransferenciaPortaImp : TransferenciaPorta {

    private val contaRepositorioPorta: ContaRepositorioPorta
    private val transferencia: Transferencia

    @Inject
    constructor(repositorio: ContaRepositorioPorta, transferencia: Transferencia) {
        this.contaRepositorioPorta = repositorio
        this.transferencia = transferencia
    }

    override fun buscarConta(numeroConta: String?) {
        if (numeroConta != null) {
            contaRepositorioPorta.buscarConta(numeroConta)
        }
        Erro.obrigatorio("O numero da conta ")
    }

    override fun transferir(contaCredito: String, contaDebito: String, valor: BigDecimal) {
        if (isNull(contaDebito)) {
            Erro.obrigatorio("Conta débito")
        }
        if (isNull(contaCredito)) {
            Erro.obrigatorio("Conta crédito")
        }
        if (isNull(valor)) {
            Erro.obrigatorio("O valor")
        }

        var debito = contaRepositorioPorta.buscarConta(contaDebito)
        var credito = contaRepositorioPorta.buscarConta(contaCredito)
        if (isNull(debito)) {
            Erro.inexistente("Conta débito")
        }
        if (isNull(credito)) {
            Erro.inexistente("Conta crédito")
        }

        if (debito?.numeroConta?.equals(credito?.numeroConta)!!) {
            Erro.mesmaConta()
        }

        transferencia.processar(valor, debito, credito)
        contaRepositorioPorta.alterar(debito)
        contaRepositorioPorta.alterar(credito)

    }
}