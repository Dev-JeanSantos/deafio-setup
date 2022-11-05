package com.example.desafiosetup.adapter.web.v1.api

import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("v1/contas")
interface ContaApi {

    @PutMapping("/transferencias")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun transferenciaConta(@RequestBody @Valid transferenciaRequest: TransferenciaRequest): CorrentistaResponse

}