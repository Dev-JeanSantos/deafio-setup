package com.example.desafiosetup.adapter.output.dynamodb.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.example.desafiosetup.adapter.output.dynamodb.converter.toDomain
import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.output.dynamodb.helper.RepositoryDynamoDBHelper
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Repository

@Repository
class CorrentistaRepository(
    private val dynamoDBMapper: DynamoDBMapper
):CorrentistaRepositorioPorta{
    override fun salvar(correntista: Correntista): Correntista{
        dynamoDBMapper.save(correntista.toModel())
        return correntista
    }


    override fun buscarCorrentistaPorNumeroConta(numeroConta: String): Correntista {
        val entidade = CorrentistaModel(pk = numeroConta, nome = "", conta = "")

        return dynamoDBMapper.query(CorrentistaModel::class.java, RepositoryDynamoDBHelper.construindoExpressao(entidade))
            .filter { conrrentistaEntidade -> conrrentistaEntidade.pk.isNotEmpty() }
            .map { it.toDomain() }
            .ifEmpty { throw  NegocioException(mensagem = "Correntista Inexistente")}
            .first()
    }
}