package com.example.desafiosetup.adapter.sns

import com.amazonaws.services.sns.AmazonSNS
import com.example.desafiosetup.adapter.sns.service.NotificacaoService
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SnsNotificationService: NotificacaoService {

    private val logger =  LoggerFactory.getLogger(javaClass)

    @Value("\${notification.name}")
    private lateinit var notificationName: String

    private lateinit var notificationMessagingTemplate: NotificationMessagingTemplate

    @Autowired
    fun SnsNotificationService(amazonSNS: AmazonSNS){
        this.notificationMessagingTemplate = NotificationMessagingTemplate(amazonSNS)
    }

    override fun enviarNotifcacao(subject: String, message: Any, headers: MutableMap<String, Any>) {
        this.notificationMessagingTemplate.convertAndSend(notificationName, message, headers)
    }


}