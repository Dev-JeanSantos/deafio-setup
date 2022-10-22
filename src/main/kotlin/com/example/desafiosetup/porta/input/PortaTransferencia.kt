package com.example.desafiosetup.porta.input

import java.math.BigDecimal

interface PortaTransferencia {

    fun buscarConta(numeroConta: Int)

    fun transferir(contaCredito: Int, contaDebito: Int, valor: BigDecimal)

}