package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.aplicacao.dominio.Conta
import com.example.desafiosetup.aplicacao.dominio.Erro
import org.springframework.stereotype.Service
import java.math.BigDecimal
@Service
class Transferencia {

    fun processar(valor: BigDecimal?,debito: Conta?, credito: Conta?){

        if (valor == null){
            Erro.obrigatorio("Valor da Transferência ")
        }
        if (credito == null){
            Erro.obrigatorio("Conta Crédito ")
        }
        if (debito == null){
            Erro.obrigatorio("Conta Débito ")
        }

        credito?.creditar(valor)
        debito?.debitar(valor)

    }
}