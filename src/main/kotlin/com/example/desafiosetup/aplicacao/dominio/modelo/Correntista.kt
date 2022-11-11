package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.entidade.ContaModel
import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.aplicacao.dominio.constante.Status
import java.math.BigDecimal
import java.util.UUID

data class Correntista(
    val nome: String,
    val conta: ContaType = ContaType(BigDecimal(100)),
    val idCorrentista: String = "CORRENTISTA_${UUID.randomUUID()}",
){
    fun toModel(): CorrentistaModel {
        return CorrentistaModel(
                pk = this.idCorrentista,
                nome = this.nome,
                conta = this.conta.toModel()
        )
    }
}

data class ContaType(
    val saldo: BigDecimal,
    val numero: String = "CONTA_${UUID.randomUUID()}",
    val status: Status = Status.PENDENTE
)

fun ContaType.toModel(): ContaModel {
    return ContaModel(this.saldo, this.numero, this.status)
}