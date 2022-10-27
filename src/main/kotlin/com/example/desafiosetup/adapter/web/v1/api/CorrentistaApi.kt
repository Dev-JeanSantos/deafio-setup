package com.example.desafiosetup.adapter.web.v1.api

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaResquest
import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
@RequestMapping("v1/correntistas")
interface CorrentistaApi {
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun salvarCliente(@RequestBody @Validated clienteRequest: CorrentistaResquest): CorrentistaResponse

}