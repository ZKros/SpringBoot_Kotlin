package com.acme.tour.service

import com.acme.tour.model.PromoçãoModel
import com.acme.tour.repository.PromoçãoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromoçãoServiceImpl(): PromoçãoInterface {
    @Autowired
    lateinit var promocaoRepository: PromoçãoRepository
    override fun create(promoçãoModel: PromoçãoModel) {
        this.promocaoRepository.save(promoçãoModel)
    }


    override fun getById(id: Long): PromoçãoModel? {
        return promocaoRepository.findById(id).orElseGet(null)
    }

    override fun delete(id: Long) {
        this.promocaoRepository.deleteById(id)
    }

    override fun update(id: Long, promoçãoModel: PromoçãoModel) {
        create(promoçãoModel)
    }

    override fun searchByLocal(local: String): List<PromoçãoModel> =
        listOf()

    override fun getAll(start: Int, size: Int): List<PromoçãoModel> {
        val pages: Pageable = PageRequest.of(start, size, Sort.by("local").ascending())
       return this.promocaoRepository.findAll(pages).toList()
    }

    override fun count(): Long =
        this.promocaoRepository.count()

    override fun getAllSortedByLocal(): List<PromoçãoModel> =
        this.promocaoRepository.findAll(Sort.by("local").descending()).toList()



}