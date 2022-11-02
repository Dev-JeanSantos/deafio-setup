package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro.Companion.obrigatorio
import com.example.desafiosetup.aplicacao.porta.input.TransferenciaContaUseCase
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.Objects.isNull

@Service
class ContaService: TransferenciaContaUseCase {

    override fun transferir(contaCredito: String, contaDebito: String, valor: BigDecimal) {

        if (isNull(valor)){
            obrigatorio("Valor da Transferência ")
        }
        if (isNull(contaDebito)){
            obrigatorio("Conta Débito ")
        }
        if (isNull(contaCredito)){
            obrigatorio("Conta Crédito ")
        }
//
//        contaDebito?.debitar(valor)
//        contaCredito?.creditar(valor)
    }
}