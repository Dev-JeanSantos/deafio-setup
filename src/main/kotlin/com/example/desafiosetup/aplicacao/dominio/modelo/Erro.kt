package com.example.desafiosetup.aplicacao.dominio.modelo

class Erro {

    companion object{
        fun obrigatorio(nome: String){
            throw NegocioException(nome + "é Obrigatório")
        }

        fun inexistente(nome: String){
            throw NegocioException(nome + "não encontrada")
        }

        fun saldoInsuficiente(){
            throw NegocioException("Saldo Insuficiente")
        }

        fun mesmaConta(){
            throw NegocioException("As contas de débito e crédito devem ser distintas")
        }
    }
}