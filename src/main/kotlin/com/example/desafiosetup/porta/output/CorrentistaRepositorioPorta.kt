package com.example.desafiosetup.porta.output

import com.example.desafiosetup.aplicacao.dominio.modelo.Correntista

interface CorrentistaRepositorioPorta {
    fun salvar(correntista: Correntista): Correntista
}