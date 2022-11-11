package com.example.desafiosetup.adapter.output.sns.converter

import com.amazonaws.services.sns.model.MessageAttributeValue
import com.example.desafiosetup.adapter.output.sns.event.DataItemEvent
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.fasterxml.jackson.databind.ObjectMapper

fun DataItemEvent.getMessagesAttributes(): Map<String, MessageAttributeValue> = mapOf(
        "domain" to this.domain.toMessageAtribute()
)

fun Correntista.toEvent(correntista: Correntista) = Correntista(
        nome = this.nome,
        idCorrentista = this.conta.numero
)

private fun String.toMessageAtribute() =
    MessageAttributeValue()
        .withDataType("String")
        .withStringValue(this)

fun <T> T.objectToMap(): Map<String, Any>{
    return conversor()
}

private inline fun <T, reified R> T.conversor(): R {
    val objMapper = ObjectMapper()
    val value = objMapper.writeValueAsString(this)
    return objMapper.readValue(value, R::class.java)
}
