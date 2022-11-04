package com.example.desafiosetup.adapter.web.v1.api

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.ContaResponse
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.math.BigDecimal

@RequestMapping("v1/correntistas")
interface CorrentistaApi {
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun salvarConta(@RequestBody @Validated clienteRequest: CorrentistaRequest): CorrentistaResponse

    @GetMapping("/{numeroConta}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun buscarCorrentista(
        @PathVariable numeroConta: String
    ): CorrentistaResponse

    @PutMapping("/transferencias")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun transferenciaConta(@RequestBody transferenciaRequest: TransferenciaRequest): CorrentistaResponse

}