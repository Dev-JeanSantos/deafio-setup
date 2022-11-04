package com.example.desafiosetup.adapter.output.dynamodb.converter

import com.example.desafiosetup.adapter.output.dynamodb.entidade.ContaModel
import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.output.dynamodb.entidade.toDomain
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import java.math.BigDecimal

fun Correntista.toEntity(): CorrentistaModel = CorrentistaModel(
        pk = this.nome,
        nome = this.nome,
        conta = ContaModel(BigDecimal.ZERO)
)


fun CorrentistaModel.toDomain(): Correntista = Correntista(
        nome = this.nome,
        conta = this.conta.toDomain()
)