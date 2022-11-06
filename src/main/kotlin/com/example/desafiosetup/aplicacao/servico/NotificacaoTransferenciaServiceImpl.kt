package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.sns.service.NotificacaoService
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.output.NotificacaoTransferenciaServicePorta
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class NotificacaoTransferenciaServiceImpl(
    private val notificacaoService: NotificacaoService
): NotificacaoTransferenciaServicePorta {

    private val logger =  LoggerFactory.getLogger(javaClass)

    @Async
    override fun EnviarNorificacaoTransferencia(correntista: Correntista) {
        try {
            val mapper: ObjectMapper = ObjectMapper()
            val headers: MutableMap<String, Any> = LinkedHashMap()
            headers.put("correntista", mapper.writeValueAsString(correntista))

            notificacaoService.enviarNotifcacao("SUBJETC: TRANSFERENCIA ENTRE CONTAS",
                    mapper.writeValueAsString(correntista),headers
            )
        }catch (exc: NegocioException){
            logger.error(exc.message)
        }
    }
}