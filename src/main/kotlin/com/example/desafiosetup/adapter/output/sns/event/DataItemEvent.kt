package com.example.desafiosetup.adapter.output.sns.event

import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.UUID

private const val DOMAIN = "exemplo"
private const val TIMESTAMP_PATTTERN = "yyyy-MM-dd'T'HH:mm:ss.[SSS]'z'"

data class DataItemEvent (
    val eventType: EventType,
    var data: Map<String, Any?> = emptyMap()
        ){
    val domain: String = DOMAIN
    val eventId: String = UUID.randomUUID().toString()
    val timestamp: String = dataTimeUTCNow()
    val sendTimestamp: String = dataTimeUTCNow()


    enum class EventType(@get:JsonValue val value: String){
        EVENT_NAME("transfer-completed-SNS")
    }
    private fun dataTimeUTCNow() = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(
            TIMESTAMP_PATTTERN
    ))
}