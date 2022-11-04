package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Erro.Companion.obrigatorio
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.Objects.isNull

@Service
class TransferenciaService {
    fun processar(valor: BigDecimal?, debito: Conta?, credito: Conta?){

        if (isNull(valor)){
            obrigatorio("Valor da Transferência ")
        }
        if (isNull(debito)){
            obrigatorio("Conta Débito ")
        }
        if (isNull(credito)){
            obrigatorio("Conta Crédito ")
        }

        debito?.debitar(valor)
        credito?.creditar(valor)

    }
}