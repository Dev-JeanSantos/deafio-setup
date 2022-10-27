package com.example.desafiosetup.aplicacao.servico

import com.example.desafiosetup.adapter.web.v1.response.CorrentistaResponse
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.porta.input.SalvarCorrentistaUseCase
import com.example.desafiosetup.porta.output.ContaRepositorioPorta
import com.example.desafiosetup.porta.output.CorrentistaRepositorioPorta
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID
@Service
class CorrentistaService(
    private val correntistaRepositorioPorta: CorrentistaRepositorioPorta,
    private val contaRepositorioPorta: ContaRepositorioPorta
) : SalvarCorrentistaUseCase {
    override fun salvarCorrentista(nome: String): CorrentistaResponse {
        val novaConta = contaRepositorioPorta.salvar(Conta(UUID.randomUUID().toString(), BigDecimal(100), nome))
        correntistaRepositorioPorta.salvar(Correntista(nome, novaConta))
        return CorrentistaResponse(nome, novaConta.numeroConta, novaConta.saldo)
    }
}