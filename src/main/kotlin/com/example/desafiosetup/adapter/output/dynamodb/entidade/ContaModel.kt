package com.example.desafiosetup.adapter.output.dynamodb.entidade

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum
import com.example.desafiosetup.aplicacao.dominio.modelo.ContaType
import com.example.desafiosetup.aplicacao.dominio.modelo.Status
import java.math.BigDecimal

@DynamoDBDocument
data class ContaModel(
    @DynamoDBAttribute(attributeName = "saldo")
    var saldo: BigDecimal = BigDecimal.ZERO,
    @DynamoDBAttribute(attributeName = "numero")
    var numero: String = "",
    @DynamoDBTypeConvertedEnum()
    var status: Status = Status.PENDENTE
)
fun ContaModel.toDomain(): ContaType = ContaType(
        saldo = this.saldo,
        numero = this.numero,
        status = this.status
)