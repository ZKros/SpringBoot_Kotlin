package com.acme.tour.service

import com.acme.tour.model.PromoçãoModel
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromoçãoServiceImpl: PromoçãoInterface {
    companion object{
        val initialPromoções = arrayOf(
            PromoçãoModel(1,"Viagem Topzera", "Cancun",true, 7, 4500.00),
            PromoçãoModel(2,"Viagem Topzera", "Espanha",false, 7, 5000.00),
            PromoçãoModel(3,"Viagem Topzera", "Portugal",true, 7, 2700.00),
            PromoçãoModel(4,"Viagem Topzera", "Japão",false, 7, 9000.00),
            PromoçãoModel(5,"Viagem Topzera", "Holanda",true, 7, 6500.00)
        )
    }

    var promocoes =
        ConcurrentHashMap<Long, PromoçãoModel>(initialPromoções.associateBy(PromoçãoModel::id))

    override fun create(promoçãoModel: PromoçãoModel) {
        promocoes[promoçãoModel.id] = promoçãoModel
    }


    override fun getById(id: Long): PromoçãoModel? {
        return promocoes[id]
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promoçãoModel: PromoçãoModel) {
        delete(id)
        create(promoçãoModel)
    }

    override fun searchByLocal(local: String): List<PromoçãoModel> = promocoes.filter {
            it.value.local.contains(local, true)
        }.map (Map.Entry<Long, PromoçãoModel>::value).toList()


}