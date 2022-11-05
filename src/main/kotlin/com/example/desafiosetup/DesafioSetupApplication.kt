package com.example.desafiosetup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = arrayOf("com.example.desafiosetup", "com.example.desafiosetup.adapter.input", "com.example.desafiosetup.adapter.sns.config"))
class DesafioSetupApplication

fun main(args: Array<String>) {
	runApplication<DesafioSetupApplication>(*args)
}
