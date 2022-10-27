package com.example.desafiosetup.adapter.output.dynamodb

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import javax.persistence.*

@Entity
data class CorrentistaModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    @OneToOne(targetEntity = ContaModel::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_model_id")
    val conta: Conta

) {
    fun toCorrentista() = Correntista(
            nome = nome,
            conta = conta
    )
}

fun Correntista.toCorrentista() = CorrentistaModel(
        nome = nome,
        conta = conta
)