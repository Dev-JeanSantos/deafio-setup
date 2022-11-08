package com.example.desafiosetup.adapter.web.v1.converter

import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.adapter.web.v1.response.TransferenciaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.servico.TransferenciaService


fun TransferenciaRequest.toDomain() = Conta(
        numeroConta = this.contaCredito,
        correntista = this.contaDebito,
        saldo = this.valor
)

fun Correntista.toResponse() = CorrentistaResponse(
        nome = this.nome,
        saldo = this.conta.saldo,
        idCorrentista = this.idCorrentista,
        numeroConta = this.nome
)

//fun CorrentistaResponse.toTransferenciaResponse() = TransferenciaResponse(
//        nome = this.nome,
//        saldo = this.saldo,
//        idCorrentista = this.idCorrentista,
//        numeroConta = this.nome
//)