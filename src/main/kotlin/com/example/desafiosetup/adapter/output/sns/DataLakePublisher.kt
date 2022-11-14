package com.example.desafiosetup.adapter.output.sns

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import com.example.desafiosetup.adapter.output.sns.config.AmazonSnsProperties
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import com.example.desafiosetup.aplicacao.porta.output.DataSnsPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
data class DataLakePublisher(
    private val snsClient: AmazonSNS,
    private val snsProperties: AmazonSnsProperties,
    private val objectMapper: ObjectMapper
    ):DataSnsPort {

    private val logger =  LoggerFactory.getLogger(javaClass)

    override fun envioTransferencia(transferencia: Transferencia) {
        publishSnsMessage(transferencia)
        logger.info("Enviando mensagem com eventoID - {} com SNS", transferencia)

    }
    private fun publishSnsMessage(item: Transferencia){
        try {
            val publishRequest = PublishRequest()
                .withTopicArn(snsProperties.arn)
                .withMessage(objectMapper.writeValueAsString(item))
            val messageSend = snsClient.publish(publishRequest)
            logger.info("Finalizando o envio da mensagemID - {} com SNS", messageSend.messageId)
        }catch (ex: NegocioException){
            logger.warn("Erro ao enviar SNS")
            ex.message
        }
    }
}