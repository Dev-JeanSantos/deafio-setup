package com.example.desafiosetup.adapter.sns

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import com.example.desafiosetup.adapter.sns.config.AmazonSnsProperties
import com.example.desafiosetup.adapter.sns.converter.getMessagesAttributes
import com.example.desafiosetup.adapter.sns.converter.objectToMap
import com.example.desafiosetup.adapter.sns.converter.toEvent
import com.example.desafiosetup.adapter.sns.event.DataItemEvent
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.output.DataSnsPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
data class DataLakePublisher(
    private val snsClient: AmazonSNS,
    private val snsProperties: AmazonSnsProperties,

):DataSnsPort {

    private val logger =  LoggerFactory.getLogger(javaClass)

    override fun envioTransferencia(correntista: Correntista) {
        logger.info("Enviando evento por sns")

        val item = DataItemEvent(
                eventType = DataItemEvent.EventType.EVENT_NAME,
                data = correntista.toEvent(correntista).objectToMap()
        )

    }

    private fun publishSnsMessage(item: DataItemEvent){
        try {
            val publishRequest = PublishRequest()
                .withTopicArn(snsProperties.arn)
                .withMessage(item.toString())
                .withMessageAttributes(item.getMessagesAttributes())

            val messageSend = snsClient.publish(publishRequest)
            logger.info("fim do envio por sns")
        }catch (ex: NegocioException){
            logger.warn("Erro ao enviar SNS")
            ex.message
        }
    }
}