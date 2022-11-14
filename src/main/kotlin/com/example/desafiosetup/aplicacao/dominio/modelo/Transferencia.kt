package com.example.desafiosetup.aplicacao.dominio.modelo

import com.example.desafiosetup.adapter.output.dynamodb.entidade.TransferenciaModel
import java.math.BigDecimal

data class Transferencia(
    var valor: BigDecimal,
    var contaDebito: String,
    var contaCredito: String,
    var transferenciaId: String
) {

    fun toModel() = TransferenciaModel(
            valor = this.valor,
            contaRemetente = this.contaDebito,
            contaDestino = this.contaCredito,
            caminhoS3 = ""
    )
}
