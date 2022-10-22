package com.example.desafiosetup.adapter.output

import com.example.desafiosetup.aplicacao.dominio.Conta
import com.example.desafiosetup.aplicacao.dominio.NegocioException
import com.example.desafiosetup.porta.output.ContaRepositorio
import org.springframework.stereotype.Service
import java.math.BigDecimal

import java.util.Objects.isNull


@Service
class AdaptadorContaFakeImp : ContaRepositorio{

    private val banco: MutableMap<Int, Conta> = HashMap()

    constructor(){
        banco[10] = Conta(100, BigDecimal(1000), "Jean Santos")
        banco[11] = Conta(101, BigDecimal(50000), "Jeff Francisco")
        banco[12] = Conta(102, BigDecimal(10000), "Jocelio")
    }

    override fun buscarConta(numeroConta: Int): Conta? {
        println("FAKE BANCO DE DADOS -> CONTA GET(NUMERO)")
        return banco[numeroConta]
    }

    override fun alterar(conta: Conta?) {
        println("FAKE BANCO DE DADOS -> ALTERAR CONTA")

        var cta = banco.get(conta.numeroConta)
        if (!isNull(cta)){
            banco[conta.numeroConta] = conta
        }else{
            throw NegocioException("Conta inexistente" + conta.numeroConta)
        }

    }
}