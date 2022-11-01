package com.example.desafiosetup.adapter.output.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
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
}