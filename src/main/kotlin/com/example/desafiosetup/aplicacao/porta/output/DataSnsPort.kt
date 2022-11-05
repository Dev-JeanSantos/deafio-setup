package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

interface DataSnsPort {
    fun envioTransferencia(correntista: Correntista)
}