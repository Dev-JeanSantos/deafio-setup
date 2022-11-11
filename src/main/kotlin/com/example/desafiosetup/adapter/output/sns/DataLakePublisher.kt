package com.example.desafiosetup.adapter.output.sns

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import com.example.desafiosetup.adapter.output.sns.config.AmazonSnsProperties
import com.example.desafiosetup.adapter.output.sns.converter.getMessagesAttributes
import com.example.desafiosetup.adapter.output.sns.converter.objectToMap
import com.example.desafiosetup.adapter.output.sns.converter.toEvent
import com.example.desafiosetup.adapter.output.sns.event.DataItemEvent
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import com.example.desafiosetup.aplicacao.porta.output.DataSnsPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
data class DataLakePublisher(
    private val snsClient: AmazonSNS,
    private val snsProperties: AmazonSnsProperties,

    ):DataSnsPort {

    private val logger =  LoggerFactory.getLogger(javaClass)

    override fun envioTransferencia(transferencia: Transferencia) {

        val item = DataItemEvent(
                eventType = DataItemEvent.EventType.EVENT_NAME,
                data = transferencia.objectToMap()
        )
        logger.info("Enviando mensagem com eventoID - {} com SNS", item.eventId)
        publishSnsMessage(item)

    }

    private fun publishSnsMessage(item: DataItemEvent){
        try {
            val publishRequest = PublishRequest()
                .withTopicArn(snsProperties.arn)
                .withMessage(item.toString())
                .withMessageAttributes(item.getMessagesAttributes())

            val messageSend = snsClient.publish(publishRequest)
            logger.info("Finalizando o envio do eventoID - {} e da mensagemID - {} com SNS",
                    item.eventId, messageSend.messageId)
        }catch (ex: NegocioException){
            logger.warn("Erro ao enviar SNS")
            ex.message
        }
    }
}