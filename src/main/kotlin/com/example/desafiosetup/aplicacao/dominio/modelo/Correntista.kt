package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import java.util.UUID

class Correntista(
    val nome: String,
    val conta: String
){
    fun toModel(): CorrentistaModel {
        return CorrentistaModel(
                pk = "CORRENTISTA_${UUID.randomUUID()}",
                nome = this.nome,
                conta = "SALDO: R$${this.conta}"
        )
    }
}