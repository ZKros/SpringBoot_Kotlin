package com.acme.tour.service

import com.acme.tour.model.PromoçãoModel

interface PromoçãoInterface {
    fun create(promoçãoModel: PromoçãoModel)
    fun getById(id: Long): PromoçãoModel?
    fun delete(id: Long)
    fun update(id: Long, promoçãoModel: PromoçãoModel)
    fun searchByLocal(local: String): List<PromoçãoModel>
    fun getAll(start: Int, size: Int): List<PromoçãoModel>
    fun count(): Long
    fun getAllSortedByLocal(): List<PromoçãoModel>
}