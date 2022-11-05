package com.example.desafiosetup.adapter.web.v1.api

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("v1/correntistas")
interface CorrentistaApi {
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun salvarConta(@RequestBody @Valid clienteRequest: CorrentistaRequest): CorrentistaResponse

    @GetMapping("/{numeroConta}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun buscarCorrentista(
        @PathVariable numeroConta: String
    ): CorrentistaResponse
}