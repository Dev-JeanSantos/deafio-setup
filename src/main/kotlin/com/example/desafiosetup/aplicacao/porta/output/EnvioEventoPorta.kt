package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta

interface EnvioEventoPorta {
    fun EnviarEvento(conta: Conta)
}