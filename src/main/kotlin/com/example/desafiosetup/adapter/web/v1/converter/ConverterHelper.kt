package com.example.desafiosetup.adapter.web.v1.converter

import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta


fun TransferenciaRequest.toDomain() = Conta(
        numeroConta = this.contaCredito,
        correntista = this.contaDebito,
        saldo = this.valor
)