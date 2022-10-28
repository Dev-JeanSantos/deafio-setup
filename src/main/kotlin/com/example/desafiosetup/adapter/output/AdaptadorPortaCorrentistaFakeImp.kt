package com.example.desafiosetup.adapter.output

import com.example.desafiosetup.adapter.output.dynamodb.*
import com.example.desafiosetup.aplicacao.dominio.modelo.Conta
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.NegocioException
import com.example.desafiosetup.aplicacao.porta.output.ContaRepositorioPorta
import com.example.desafiosetup.aplicacao.porta.output.CorrentistaRepositorioPorta
import javax.inject.Named

import java.util.Objects.isNull
import java.util.Optional

@Named
class AdaptadorPortaCorrentistaFakeImp(
    private val correntistaRepository: CorrentistaRepository
) : CorrentistaRepositorioPorta {
    override fun salvar(correntista: Correntista): Correntista {
        return correntistaRepository.save(correntista.toCorrentista()).toCorrentista()
    }


}
