package com.example.desafiosetup.adapter.output

import com.example.desafiosetup.adapter.output.dynamodb.ContaRepository
import com.example.desafiosetup.adapter.output.dynamodb.toContaModel
import com.example.desafiosetup.aplicacao.dominio.Conta
import com.example.desafiosetup.aplicacao.dominio.Erro
import com.example.desafiosetup.aplicacao.dominio.NegocioException
import com.example.desafiosetup.porta.output.ContaRepositorioPorta
import javax.inject.Named

import java.util.Objects.isNull


@Named
class AdaptadorPortaContaFakeImp(
    private val contaRepository: ContaRepository
) : ContaRepositorioPorta {

    override fun salvar(conta: Conta): Conta {
        return contaRepository.save(conta.toContaModel()).toConta()
    }

    override fun buscarConta(numeroConta: Int): Conta? {
        val possibleConta = contaRepository.findByNumeroConta(numeroConta)
        if (!isNull(possibleConta)) {
            return (possibleConta).toConta()
        }
        throw NegocioException("Conta Inexistente")
    }

    override fun alterar(conta: Conta?) {

        println("FAKE BANCO DE DADOS -> ALTERAR CONTA")
        val cta = contaRepository.findById()
        banco.get(conta?.numeroConta)
        if (!isNull(cta)) {
            if (conta != null) {
                banco[conta.numeroConta] = conta
            }
        } else {
            throw NegocioException("Conta inexistente" + conta?.numeroConta)
        }

    }
}



