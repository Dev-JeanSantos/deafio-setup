package com.example.desafiosetup.integracao.casouso

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
        "com.example.desafiosetup.aplicacao",
        "com.example.desafiosetup.adapter"
)
class Build1 {

}