package com.example.desafiosetup.adapter.web.v1.converter

import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista


fun TransferenciaRequest.toDomain() = Conta(
        numeroConta = this.contaCredito,
        correntista = this.contaDebito,
        saldo = this.valor
)

fun Correntista.toResponse() = CorrentistaResponse(
        nome = this.nome,
        saldo = this.conta.toBigDecimal(),
        numeroConta = this.nome
)