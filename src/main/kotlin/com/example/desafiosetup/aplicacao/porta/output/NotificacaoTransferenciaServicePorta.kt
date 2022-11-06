package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

interface NotificacaoTransferenciaServicePorta {
    fun EnviarNorificacaoTransferencia(correntista: Correntista)
}