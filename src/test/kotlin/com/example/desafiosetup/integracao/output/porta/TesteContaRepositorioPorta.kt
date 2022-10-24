package com.example.desafiosetup.integracao.output.porta

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.integracao.BaseDatabaseIntegrationsTest
import com.example.desafiosetup.porta.output.ContaRepositorioPorta
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

class TesteContaRepositorioPorta: BaseDatabaseIntegrationsTest() {

    @Autowired
    lateinit var contaRepositorioPorta: ContaRepositorioPorta

    @Test
    fun `Deve retornar sucesso ao persistir uma nova conta no banco de dados`() {
        val possivelConta = Conta(100, BigDecimal.TEN, "Jean Santos")
        val contaSalva = contaRepositorioPorta.salvar(possivelConta)

        assertEquals(possivelConta, contaSalva)
    }

    @Test
    fun `Deve retornar sucesso uma conta quando passarmos um numero de conta valido`() {
        val possivelConta = Conta(100, BigDecimal(10.00), "Jean Santos")
        val contaSalva = contaRepositorioPorta.buscarConta(100)

        assertEquals(possivelConta, contaSalva)
    }

//    @Test
//    fun `Deve retornar exceção quando quando passarmos um numero de conta invalido`() {
//
//        val exception = assertThrows<NegocioException> {
//            val contaSalva = contaRepositorioPorta.buscarConta(1)
//            fail("Conta Inexistente")
//        }
//        Assertions.assertThat(exception.message).isEqualTo("Conta Inexistente")
//    }
}