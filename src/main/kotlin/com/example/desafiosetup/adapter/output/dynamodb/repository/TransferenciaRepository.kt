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
    override fun salvarTransferencia(transferencia: Transferencia) {
        dynamoDBMapper.save(transferencia.toModel())
    }

    override fun confirmarTransferencia(contaDebito: String, contaCredito: String, reciboS3: String): BigDecimal {
        val transacao = dynamoDBMapper.query(
                TransferenciaModel::class.java,
                DynamoDBQueryExpression<TransferenciaModel>()
                    .withKeyConditionExpression("pk = :pk and sk = :sk")
                    .withExpressionAttributeValues(mapOf(
                            ":pk" to AttributeValue().withS("TRANSFERENCIA_CORRENTISTA_${contaDebito}"),
                            ":sk" to AttributeValue().withS("CORRENTISTA_${contaCredito}")
                    ))
        ).first()
        dynamoDBMapper.save(transacao.copy(status = Status.APROVADO, caminhoS3 = reciboS3))
        return transacao.valor
    }

}