package com.example.desafiosetup.porta.input

import java.math.BigDecimal

interface TransferenciaPorta {

    fun buscarConta(numeroConta: String?)

    fun transferir(contaCredito: String, contaDebito: String, valor: BigDecimal)

}