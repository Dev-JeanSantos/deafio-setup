package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.CorrentistaModel
import java.util.*

class Correntista(
    val nome: String,
    val conta: String
){
    fun toModel(): CorrentistaModel {
        return CorrentistaModel(
                pk = "CORRENTISTA#${UUID.randomUUID()}",
                nome = this.nome,
                conta = "SALDO: R$${this.conta}"
        )
    }
}