package com.example.desafiosetup.adapter.output.sns

import com.amazonaws.services.sns.AmazonSNS
import com.example.desafiosetup.adapter.output.sns.config.SnsConfig
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.porta.output.EnvioEventoPorta

data class EnvioPublisher(
    private val snsClient: AmazonSNS,
    private val snsProperties: SnsConfig
) : EnvioEventoPorta{
    override fun EnviarEvento(conta: Conta) {
//        val item =


    }
}