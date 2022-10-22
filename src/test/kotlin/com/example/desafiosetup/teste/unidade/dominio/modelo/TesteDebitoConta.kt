package com.example.desafiosetup.teste.unidade.dominio.modelo

import com.example.desafiosetup.aplicacao.dominio.Conta
import com.example.desafiosetup.aplicacao.dominio.NegocioException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class TesteDebitoConta {

    private val cem: BigDecimal = BigDecimal(100)
    private lateinit var contaValida: Conta

    @BeforeEach
    fun setup() {
        contaValida = Conta(10, cem, "Jean Santos")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor de débito for nulo`() {

        val exception = assertThrows<NegocioException> {
            contaValida.debitar(null)
        }
        assertThat(exception.message).isEqualTo("O Valor de débito é Obrigatório")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor débito for valor negativo`() {

        val exception = assertThrows<NegocioException> {
            contaValida.debitar(BigDecimal(-100))
        }
        assertThat(exception.message).isEqualTo("O Valor de débito positivo é Obrigatório")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor credito for valor 0`() {

        val exception = assertThrows<NegocioException> {
            contaValida.debitar(BigDecimal(0))
        }
        assertThat(exception.message).isEqualTo("O Valor de débito positivo é Obrigatório")
    }

    @Test
    fun `Deve retornar uma exceção obrigatória quando o valor débito for valor acima do saldo de conta`() {

        val exception = assertThrows<NegocioException> {
            contaValida.debitar(cem.add(BigDecimal.ONE))
        }
        assertThat(exception.message).isEqualTo("Saldo Insuficiente")
    }

    @Test
    fun `Deve retornar zero na conta quando o valor de débito for igual ao saldo `() {
        contaValida.debitar(cem)
        assertThat(contaValida.saldo).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `Deve retornar sucesso ao debitar na conta quando um valor de débito for valor um positivo e menor que o saldo`() {
        contaValida.debitar((BigDecimal.ONE))
        var saldoFinal: BigDecimal = cem.subtract(BigDecimal.ONE)
        assertThat(contaValida.saldo).isEqualTo(saldoFinal)
    }
}