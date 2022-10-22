package com.example.desafiosetup.porta.output

import com.example.desafiosetup.aplicacao.dominio.Conta

interface ContaRepositorio {
    fun buscarConta(numeroConta: Int): Conta?
    fun alterar(conta: Conta?)
}