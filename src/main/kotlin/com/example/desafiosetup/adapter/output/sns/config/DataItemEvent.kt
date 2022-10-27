package com.example.desafiosetup.adapter.output.sns.config

import com.fasterxml.jackson.annotation.JsonValue
import java.util.UUID

private const val DOMAIN = "DOMAIN"


data class DataItemEvent(
    val eventType: EventType,
    var data: Map<String, Any?> = emptyMap()
){
//    val domain: String = DOMAI
    val eventId: String = UUID.randomUUID().toString()

    enum class EventType(@get:JsonValue val value: String){
        EVENT_NAME("exemplo")
    }
}