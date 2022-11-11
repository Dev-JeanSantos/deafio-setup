package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import java.math.BigDecimal

interface TransferenciaPorta {
    fun salvarTransferencia(transferencia: Transferencia)

    fun confirmarTransferencia(contaDebito: String, contaCredito: String, reciboS3: String): BigDecimal
}