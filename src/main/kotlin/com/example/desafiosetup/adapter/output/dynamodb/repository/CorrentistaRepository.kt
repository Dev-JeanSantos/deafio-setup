package com.example.desafiosetup.adapter.output.dynamodb.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class CorrentistaRepository(
    private val dynamoDBMapper: DynamoDBMapper
) : CorrentistaRepositorioPorta {
    override fun salvar(correntista: Correntista): Correntista {
        dynamoDBMapper.save(correntista.toModel())
        return correntista
    }


    override fun buscarCorrentistaPorNumeroConta(numeroConta: String): CorrentistaModel {
        return dynamoDBMapper.query(
                CorrentistaModel::class.java,
                DynamoDBQueryExpression<CorrentistaModel>()
                    .withKeyConditionExpression("pk = :pk")
                    .withExpressionAttributeValues(mapOf(":pk" to AttributeValue().withS(numeroConta)))
        ).first()
    }

    override fun transferirValoresEntreContas(debito: CorrentistaModel, credito: CorrentistaModel): CorrentistaModel {
        // Recebe o modelo do objeto já com saldo atualizado e atualiza
        dynamoDBMapper.save(debito)
        dynamoDBMapper.save(credito)
        // Retorna a entidade de quem recebeu o credito
        return credito
    }

    override fun confirmarTransferencia(contaConfirmadaTransferencia: CorrentistaModel): CorrentistaResponse {
        dynamoDBMapper.save(contaConfirmadaTransferencia)
        return contaConfirmadaTransferencia.toResponse()
    }
}