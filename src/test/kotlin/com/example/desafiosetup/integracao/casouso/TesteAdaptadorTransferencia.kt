package com.example.desafiosetup.integracao.casouso

import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.integracao.BaseDatabaseIntegrationsTest
import com.example.desafiosetup.porta.input.TransferenciaPorta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import javax.inject.Inject
import java.util.Objects.isNull

class TesteAdaptadorTransferencia(
) : BaseDatabaseIntegrationsTest() {


    private val contaCredito: Int = 10
    private val contaDebito: Int = 20
    private val contaInexistente: Int = 30
    private val cem: BigDecimal = BigDecimal(100)
    private val cinquenta: BigDecimal = BigDecimal(50)

    @Inject
    lateinit var transferenciaPorta: TransferenciaPorta

    @Test
    fun `Deve retornar uma exceção obrigatória quando o numero da conta for nulo`() {
        try {
            var conta = transferenciaPorta.buscarConta(null)
            println(conta)
            assertTrue(isNull(conta))
        } catch (e: NegocioException) {
            assertThat(e.message).isEqualTo("O numero da conta é Obrigatório")
        }
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o numero da conta inexistente`() {
        try {
            var conta = transferenciaPorta.buscarConta(contaCredito)

            assertTrue(isNull(conta))
        } catch (e: NegocioException) {
            assertThat(e.message).isEqualTo("O numero da conta é Obrigatório")
        }
    }

}