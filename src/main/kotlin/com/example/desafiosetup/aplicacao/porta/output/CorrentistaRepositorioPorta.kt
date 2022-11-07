package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.adapter.output.dynamodb.entidade.CorrentistaModel
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

interface CorrentistaRepositorioPorta {
    fun salvar(correntista: Correntista): Correntista

    fun buscarCorrentistaPorNumeroConta(numeroConta: String): CorrentistaModel

    fun transferirValoresEntreContas(debito: CorrentistaModel, credito: CorrentistaModel): CorrentistaModel
    fun confirmarTransferencia(contaConfirmadaTransferencia: CorrentistaModel): CorrentistaResponse
}