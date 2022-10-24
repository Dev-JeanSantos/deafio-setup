package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.aplicacao.dominio.modelo.Erro.Companion.obrigatorio
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro.Companion.saldoInsuficiente
import java.math.BigDecimal
import java.util.Objects.isNull

data class Conta(
    val numeroConta: Int = 0,
    var saldo: BigDecimal = BigDecimal.ZERO,
    val correntista: String = "Não Informado"
){
    fun  creditar(credito: BigDecimal?){
        if (isNull(credito)){
            obrigatorio("O Valor de crédito ")
        }
        if (credito!! <= BigDecimal.ZERO){
            obrigatorio("O Valor de crédito positivo ")
        }

        saldo = saldo.add(credito)
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

    }
}