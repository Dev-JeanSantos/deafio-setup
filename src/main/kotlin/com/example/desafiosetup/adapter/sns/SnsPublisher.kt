package com.example.desafiosetup.adapter.sns

import com.amazonaws.services.sns.AmazonSNS
import com.example.desafiosetup.adapter.sns.config.AmazonSnsProperties
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.porta.output.DataSnsPort
import org.springframework.stereotype.Service

@Service
data class SnsPublisher(
    private val snsClient: AmazonSNS,
    private val snsProperties: AmazonSnsProperties
): DataSnsPort {
    override fun envioTransferencia(correntista: Correntista) {
        TODO("Not yet implemented")
    }


}