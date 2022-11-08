package com.example.desafiosetup.adapter.web.v1.api

import com.example.desafiosetup.adapter.web.v1.request.CorrentistaTransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.request.TransferenciaRequest
import com.example.desafiosetup.adapter.web.v1.response.TransferenciaResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("v1/contas")
interface ContaApi {

    @PutMapping("/transferencias")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun transferenciaConta(@RequestBody @Valid transferenciaRequest: TransferenciaRequest): TransferenciaResponse

    @PutMapping("/confirmar_transferencias")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun confirmaTransferenciaConta(@RequestBody @Valid correntistaTransferenciaRequest: CorrentistaTransferenciaRequest): TransferenciaResponse


}