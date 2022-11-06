package com.example.desafiosetup.adapter.sns.service

import org.springframework.stereotype.Service

@Service
interface NotificacaoService {

    fun enviarNotifcacao(subject: String, message: Any, headers: MutableMap<String, Any>)

}