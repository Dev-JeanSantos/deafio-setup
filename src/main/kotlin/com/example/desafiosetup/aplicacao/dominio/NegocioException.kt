package com.example.desafiosetup.aplicacao.dominio

class NegocioException: RuntimeException {
    constructor(mensagem: String): super(mensagem)
}