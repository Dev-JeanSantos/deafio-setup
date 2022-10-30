package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.CorrentistaModel
import com.example.desafiosetup.adapter.output.dynamodb.CorrentistaModel2

class Correntista2(
    val nome: String,
    val conta: String
){
    fun toModel(): CorrentistaModel2{
        return CorrentistaModel2(
                pk = "CORRENTISTA#${this.nome}",
                nome = this.nome,
                conta = this.conta
        )
    }
}