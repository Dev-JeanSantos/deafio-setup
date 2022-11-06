package com.example.desafiosetup.adapter.sns.event

import com.fasterxml.jackson.annotation.JsonValue

private const val DOMAIN = "exemplo"
private const val SCHEMA_VERSION = "1.0"
private const val ORG_ID = "EXEMPLO"
private const val TIMESTAMP_PATTTERN = "yyyy-MM-dd'T'HH:mm:ss.[SSS]'z'"

data class DataItemEvent (
    val eventType: EventType,
    var data: Map<String, Any?> = emptyMap()
        ){
    val domain: String = DOMAIN



    enum class EventType(@get:JsonValue val value: String){
        EVENT_NAME("transfer-completed-SNS")
    }
}