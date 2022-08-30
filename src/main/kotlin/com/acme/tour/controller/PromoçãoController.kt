package com.acme.tour.controller

import com.acme.tour.exception.PromoçãoNotFounException
import com.acme.tour.model.ErrorMessage
import com.acme.tour.model.PromoçãoModel
import com.acme.tour.model.RespostaJSON
import com.acme.tour.service.PromoçãoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/promocao"])
class PromoçãoController {

    @Autowired
    lateinit var promoçãoService: PromoçãoServiceImpl

    @GetMapping("/{id}")
    fun getId(@PathVariable id: Long): ResponseEntity<Any?> {
        var promoção = promoçãoService.getById(id)
        return if(promoção != null)
            return ResponseEntity(promoção, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promoção não localizada", "Promoção ${id} não localizado"), HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun create(@RequestBody promoção: PromoçãoModel): ResponseEntity<RespostaJSON> {
        promoçãoService.create(promoção)
        val respostaJSON = RespostaJSON ("Promoção Criada", Date())
        return ResponseEntity(respostaJSON, HttpStatus.CREATED )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<RespostaJSON>{
        var status = HttpStatus.NOT_FOUND
        val respostaJSON = RespostaJSON ("Promoção Deletada", Date())
        if (this.promoçãoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            promoçãoService.delete(id)
        }
        return ResponseEntity(respostaJSON, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promoção: PromoçãoModel): ResponseEntity<Unit>{
        this.promoçãoService.update(id, promoção)
        var status = HttpStatus.NOT_FOUND
        if (this.promoçãoService.getById(id)!= null){
            this.promoçãoService.update(id, promoção)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "5") size: Int) : ResponseEntity<List<PromoçãoModel>
    {
        val list = this.promoçãoService.getAll(start, size)
        val status = if(list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(list, status)
    }
    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String, Long>> =
         ResponseEntity.ok().body(mapOf("count" to this.promoçãoService.count()))
    @GetMapping("/ordenanos")
    fun ordenados() = this.promoçãoService.getAllSortedByLocal()

    }
