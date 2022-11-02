package com.example.desafiosetup.adapter.output.dynamodb.converter

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

fun Correntista.toEntity(): CorrentistaModel = CorrentistaModel(
        pk = this.nome,
        nome = this.nome,
        conta = this.conta
)


fun CorrentistaModel.toDomain(): Correntista = Correntista(
        nome = this.nome,
        conta = this.conta
)