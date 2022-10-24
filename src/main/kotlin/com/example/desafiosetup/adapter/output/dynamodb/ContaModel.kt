package com.example.desafiosetup.adapter.output.dynamodb

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import java.math.BigDecimal
import java.math.MathContext
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ContaModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val numeroConta: Int = 1,
    val saldo: BigDecimal = BigDecimal.ZERO,
    var correntista: String = ""
)
{
    fun toConta() = Conta(
            numeroConta = numeroConta,
            saldo = saldo.round(MathContext(2)),
            correntista = correntista
    )
}

fun Conta.toContaModel() = ContaModel(
        numeroConta = numeroConta,
        saldo = saldo,
        correntista = correntista
)