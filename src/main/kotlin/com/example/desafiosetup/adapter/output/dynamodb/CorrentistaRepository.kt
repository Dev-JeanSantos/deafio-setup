package com.example.desafiosetup.adapter.output.dynamodb

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CorrentistaRepository: JpaRepository<CorrentistaModel, Long>