package com.example.desafiosetup.aplicacao.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

interface CorrentistaRepositorioPorta {
    fun salvar(correntista: Correntista): Correntista
}