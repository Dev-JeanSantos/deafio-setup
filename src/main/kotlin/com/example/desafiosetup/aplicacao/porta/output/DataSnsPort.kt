package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.Transferencia

interface DataSnsPort {
    fun envioTransferencia(transferencia: Transferencia)
}