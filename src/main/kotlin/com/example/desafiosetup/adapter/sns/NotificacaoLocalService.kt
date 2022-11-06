package com.example.desafiosetup.adapter.sns

import com.example.desafiosetup.adapter.sns.service.NotificacaoService
import org.slf4j.LoggerFactory

class NotificacaoLocalService: NotificacaoService {

    private val logger =  LoggerFactory.getLogger(javaClass)

    override fun enviarNotifcacao(subject: String, message: Any, headers: MutableMap<String, Any>) {
        logger.info("Enviando mensagem com LocalNotificationService: %s, %s", subject, message)
    }

}