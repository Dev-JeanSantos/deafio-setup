package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.CorrentistaModel2
import java.util.*

class Correntista2(
    val nome: String,
    val conta: String
){
    fun toModel(): CorrentistaModel2{
        return CorrentistaModel2(
                pk = "CORRENTISTA#${UUID.randomUUID()}",
                nome = this.nome,
                conta = "SALDO: R$${this.conta}"
        )
    }
}