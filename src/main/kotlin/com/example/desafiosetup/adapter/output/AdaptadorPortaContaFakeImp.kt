package com.example.desafiosetup.adapter.output

import com.example.desafiosetup.adapter.output.dynamodb.ContaModel
import com.example.desafiosetup.adapter.output.dynamodb.ContaRepository
import com.example.desafiosetup.adapter.output.dynamodb.toContaModel
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.output.ContaRepositorioPorta
import javax.inject.Named

import java.util.Objects.isNull
import java.util.Optional

@Named
class AdaptadorPortaContaFakeImp(
    private val contaRepository: ContaRepository
) : ContaRepositorioPorta {

    override fun salvar(conta: Conta): Conta {
        return contaRepository.save(conta.toContaModel()).toConta()
    }

    override fun buscarConta(numeroConta: String): Conta? {

        val possibleConta: Optional<ContaModel> = contaRepository.findByNumeroConta(numeroConta)
        if (!isNull(possibleConta)) {
            return (possibleConta).get().toConta()
        } else {
            throw NegocioException("Conta Inexistente")
        }
    }

    override fun alterar(conta: Conta?) {

        println("FAKE BANCO DE DADOS -> ALTERAR CONTA")
        val cta = contaRepository.findByNumeroConta(conta?.numeroConta!!)
        if (!isNull(cta)) {
            contaRepository.save(conta.toContaModel()).toConta()
        } else {
            throw NegocioException("Conta inexistente" + conta?.numeroConta)
        }
    }
}



