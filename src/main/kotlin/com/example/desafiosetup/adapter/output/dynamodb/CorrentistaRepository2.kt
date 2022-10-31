package com.example.desafiosetup.adapter.output.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista2
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta2
import org.springframework.stereotype.Repository

@Repository
class CorrentistaRepository2(
    private val dynamoDBMapper: DynamoDBMapper
):CorrentistaRepositorioPorta2{
    override fun salvar(correntista: Correntista2): Correntista2{
        dynamoDBMapper.save(correntista.toModel())
        return correntista
    }
}