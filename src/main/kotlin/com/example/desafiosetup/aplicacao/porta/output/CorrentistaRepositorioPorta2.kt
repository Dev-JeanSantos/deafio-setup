package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista
import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista2

interface CorrentistaRepositorioPorta2 {
    fun salvar(correntista: Correntista2): Correntista2
}