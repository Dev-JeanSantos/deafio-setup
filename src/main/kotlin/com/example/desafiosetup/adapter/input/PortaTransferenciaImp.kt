package com.example.desafiosetup.adapter.input

import com.example.desafiosetup.aplicacao.dominio.Erro
import com.example.desafiosetup.aplicacao.servico.Transferencia
import com.example.desafiosetup.porta.input.PortaTransferencia
import com.example.desafiosetup.porta.output.ContaRepositorio
import org.springframework.stereotype.Service
import java.math.BigDecimal

import java.util.Objects.isNull

@Service
class PortaTransferenciaImp: PortaTransferencia {

    private val repositorio: ContaRepositorio = TODO()
    private val transferencia: Transferencia

    constructor(repositorio: ContaRepositorio, transferencia: Transferencia){
        this.repositorio = repositorio
        this.transferencia = transferencia
    }

    override fun buscarConta(numeroConta: Int) {
        repositorio.buscarConta(numeroConta)
    }

    override fun transferir(contaCredito: Int, contaDebito: Int, valor: BigDecimal) {
        if (isNull(contaDebito)){
            Erro.obrigatorio("Conta débito")
        }
        if (isNull(contaCredito)){
            Erro.obrigatorio("Conta crédito")
        }
        if (isNull(valor)){
            Erro.obrigatorio("O valor")
        }

        var debito = repositorio.buscarConta(contaDebito)
        var credito = repositorio.buscarConta(contaCredito)
        if (isNull(debito)){
            Erro.inexistente("Conta débito")
        }
        if (isNull(credito)){
            Erro.inexistente("Conta crédito")
        }

        if (debito?.numeroConta?.equals(credito?.numeroConta)!!){
            Erro.mesmaConta()
        }

        transferencia.processar(valor, debito, credito)
        repositorio.alterar(debito)
        repositorio.alterar(credito)



    }
}