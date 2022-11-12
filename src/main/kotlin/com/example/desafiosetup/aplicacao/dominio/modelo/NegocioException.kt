package com.example.desafiosetup.aplicacao.dominio.modelo

data class NegocioException(
    val mensagem: String
): RuntimeException()