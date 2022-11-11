package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.entidade.TransferenciaModel
import java.math.BigDecimal

data class Transferencia(
    var valor: BigDecimal,
    var contaDebito: String = "",
    var contaCredito: String
) {

    fun toModel() = TransferenciaModel(
            valor = this.valor,
            pk = this.contaDebito,
            sk = this.contaCredito,
            caminhoS3 = ""
    )
}
