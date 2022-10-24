package com.example.desafiosetup.unidade.dominio.modelo

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class TesteCreditoConta {

    private val cem: BigDecimal = BigDecimal(100)
    private lateinit var contaValida: Conta

    @BeforeEach
    fun setup() {
        contaValida = Conta(10, cem, "Jean Santos")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor credito for nulo`() {

        val exception = assertThrows<NegocioException> {
            contaValida.creditar(null)
        }
        assertThat(exception.message).isEqualTo("O Valor de crédito é Obrigatório")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor credito for valor negativo`() {

        val exception = assertThrows<NegocioException> {
            contaValida.creditar(BigDecimal(-100))
        }
        assertThat(exception.message).isEqualTo("O Valor de crédito positivo é Obrigatório")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor credito for valor 0`() {

        val exception = assertThrows<NegocioException> {
            contaValida.creditar(BigDecimal(0))
        }
        assertThat(exception.message).isEqualTo("O Valor de crédito positivo é Obrigatório")
    }

    @Test
    fun `Deve retornar sucesso quando um valor de credito for valor positivo`() {
        contaValida.creditar((BigDecimal.ONE))
        var saldoFinal: BigDecimal = cem.add(BigDecimal.ONE)
        assertThat(contaValida.saldo).isEqualTo(saldoFinal)
    }
}