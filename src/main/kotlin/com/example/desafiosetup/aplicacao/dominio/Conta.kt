package com.example.desafiosetup.aplicacao.dominio

import java.math.BigDecimal

data class Conta(
    val numeroConta: Int,
    var saldo: BigDecimal,
    val correntista: String
){
    fun  creditar(credito: BigDecimal?){
        if (credito == null){
            Erro.obrigatorio("O Valor de crédito ")
        }
        if (credito!! <= BigDecimal.ZERO){
            Erro.obrigatorio("O Valor de crédito positivo ")
        }

        saldo = saldo.add(credito)
    }

    fun  debitar(debito: BigDecimal?){
        if (debito == null){
            Erro.obrigatorio("O Valor de débito ")
        }
        if (debito!! <= BigDecimal.ZERO){
            Erro.obrigatorio("O Valor de débito positivo ")
        }
        if (debito > saldo){
            Erro.saldoInsuficiente()
        }

        saldo = saldo.subtract(debito)

    }
}