package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.entidade.ContaModel
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro.Companion.obrigatorio
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro.Companion.saldoInsuficiente
import java.math.BigDecimal
import java.util.Objects.isNull


data class Conta(
    val numeroConta: String,
    var saldo: BigDecimal,
    val correntista: String ){
    fun  creditar(credito: BigDecimal?){
        if (isNull(credito)){
            obrigatorio("O Valor de crédito ")
        }
        if (credito!! <= BigDecimal.ZERO){
            obrigatorio("O Valor de crédito positivo ")
        }

        saldo = saldo.add(credito)
        println("saldoCredito: ${saldo}")
    }

    fun  debitar(debito: BigDecimal?){
        if (debito == null){
            obrigatorio("O Valor de débito ")
        }
        if (debito!! <= BigDecimal.ZERO){
            obrigatorio("O Valor de débito positivo ")
        }
        if (debito > saldo){
            saldoInsuficiente()
        }

        saldo = saldo.subtract(debito)
        println("saldoDebito: ${saldo}")

    }
}

fun Conta.toModel(): ContaModel {
    return ContaModel(this.saldo, this.numeroConta)
}