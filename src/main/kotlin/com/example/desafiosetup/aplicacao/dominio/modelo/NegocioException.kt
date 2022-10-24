package com.example.desafiosetup.aplicacao.dominio.modelo

class NegocioException: RuntimeException {
    constructor(mensagem: String): super(mensagem)
}