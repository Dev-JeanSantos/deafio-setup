package com.example.desafiosetup.porta.output

import com.example.desafiosetup.aplicacao.dominio.Conta

interface ContaRepositorioPorta {
    fun salvar(conta: Conta): Conta
    fun buscarConta(numeroConta: Int): Conta?
    fun alterar(conta: Conta?)
}