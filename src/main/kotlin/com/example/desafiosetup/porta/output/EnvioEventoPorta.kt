package com.example.desafiosetup.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta

interface EnvioEventoPorta {

    fun EnviarEvento(conta: Conta)
}