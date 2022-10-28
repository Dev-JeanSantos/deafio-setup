package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CorrentistaRepositorioPortaTest{

    @Autowired
    private lateinit var  webApplicationContext: WebApplicationContext
    @Autowired
    private lateinit var  objectMapper: ObjectMapper
    private lateinit var  mockMvc: MockMvc



    companion object{

        private const val RECURSO = "/v1/correntista"
        private val conta = Conta("1000", BigDecimal.TEN, "Jean")
        private val correntista = Correntista("Jean", conta)
    }

    @BeforeEach
    fun setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build()
    }

    @Test
    fun `Deve retornar 404 ao fazer uma requisição sem o nome do correntista`(){
        mockMvc.post(RECURSO).andExpect {status { is4xxClientError() } }
    }

    @Test
    fun `Deve retornar 200 ao fazer uma requisição com o nome do correntista`(){

        mockMvc.post(RECURSO){
            headers {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(correntista)
            }
        }.andExpect {
            status { isOk() }
        }

    }
}