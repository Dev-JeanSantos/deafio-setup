package com.example.desafiosetup.adapter.web.v1.controller

import com.example.desafiosetup.adapter.web.v1.api.CorrentistaApi
import com.example.desafiosetup.adapter.web.v1.converter.toResponse
import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.porta.input.SalvarCorrentistaUseCase
import com.example.desafiosetup.aplicacao.porta.input.TransferenciaContaUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class CorrentistaApiController(
    @Autowired
    private val salvarCorrentistaUseCase: SalvarCorrentistaUseCase,
    @Autowired
    private val transferenciaContaUseCase: TransferenciaContaUseCase
):CorrentistaApi{

    override fun salvarConta(correntistaRequest: CorrentistaRequest): CorrentistaResponse{
        return salvarCorrentistaUseCase.salvarCorrentista(correntistaRequest)
    }

    override fun buscarCorrentista(numeroConta: String): CorrentistaResponse {
       return salvarCorrentistaUseCase.buscar(numeroConta).toResponse()
    }

    override fun transferenciaConta(@RequestBody contaCredito: String, contaDebito: String, valor: BigDecimal) {
        return transferenciaContaUseCase.transferir(contaCredito, contaDebito, valor)
    }

}