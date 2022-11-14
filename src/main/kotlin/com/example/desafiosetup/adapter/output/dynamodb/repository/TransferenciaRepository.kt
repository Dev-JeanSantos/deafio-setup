package com.example.desafiosetup.adapter.output.dynamodb.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.example.desafiosetup.adapter.output.dynamodb.entidade.TransferenciaModel
import com.example.desafiosetup.aplicacao.dominio.constante.Status
import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import com.example.desafiosetup.aplicacao.porta.input.TransferenciaPorta
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class TransferenciaRepository(
    private val dynamoDBMapper: DynamoDBMapper
) : TransferenciaPorta {
    override fun salvarTransferencia(transferencia: Transferencia): String {
        val transferenciaModel = transferencia.toModel()
        dynamoDBMapper.save(transferenciaModel)
        return transferenciaModel.pk

    }

    override fun confirmarTransferencia(transferenciaId: String, reciboS3: String): Transferencia {
        val transacao = dynamoDBMapper.query(
                TransferenciaModel::class.java,
                DynamoDBQueryExpression<TransferenciaModel>()
                    .withKeyConditionExpression("pk = :pk")
                    .withExpressionAttributeValues(mapOf(
                            ":pk" to AttributeValue().withS("TRANSFERENCIA_${transferenciaId}"),
                    ))
        ).first()
        dynamoDBMapper.save(transacao.copy(status = Status.APROVADO, caminhoS3 = reciboS3))
        return transacao.toTransferencia()
    }

}