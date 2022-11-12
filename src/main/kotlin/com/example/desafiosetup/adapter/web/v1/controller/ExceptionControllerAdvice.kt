package com.example.desafiosetup.adapter.web.v1.controller

import com.example.desafiosetup.adapter.web.v1.response.MenssagemGenericaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleIllegalStateException(ex: NegocioException): ResponseEntity<MenssagemGenericaResponse> {
        return ResponseEntity(MenssagemGenericaResponse(ex.mensagem), HttpStatus.BAD_REQUEST)
    }
}
