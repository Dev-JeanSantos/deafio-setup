package com.example.desafiosetup.aplicacao.porta.input

import java.math.BigDecimal

interface TransferenciaContaUseCase {
    fun transferir(contaCredito: String, contaDebito: String, valor:BigDecimal)
}