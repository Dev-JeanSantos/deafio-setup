package com.example.desafiosetup.aplicacao.porta.input

import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia
import java.math.BigDecimal

interface TransferenciaPorta {
    fun salvarTransferencia(transferencia: Transferencia): String

    fun confirmarTransferencia(transferenciaId: String, reciboS3: String): Transferencia
}