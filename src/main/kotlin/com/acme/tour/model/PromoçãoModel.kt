package com.acme.tour.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
class PromoçãoModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,
    val descricao: String = "",
    val local: String = "",
    val isAllInclusive: Boolean = false,
    val qtdDias: Int = 1,
    val preco: Double = 0.0,
)