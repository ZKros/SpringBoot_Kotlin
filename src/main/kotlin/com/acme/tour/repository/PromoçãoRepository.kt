package com.acme.tour.repository

import com.acme.tour.model.PromoçãoModel
import org.springframework.data.jpa.repository.Query

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PromoçãoRepository: PagingAndSortingRepository<PromoçãoModel, Long> {
    @Query(value = "SELECT p FROM Promocao p WHERE p.preco <= 9000")
    fun findByPrecoMenorQue()
}