package com.eighthours.ilohas.domain.market.data.rates

import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.domain.market.Term
import com.eighthours.ilohas.domain.market.data.MarketDataElement
import com.eighthours.ilohas.domain.market.data.MarketDataId
import org.apache.commons.collections4.keyvalue.MultiKey
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class InterestRate(
        val marketDataId: MarketDataId,
        val indexName: String,
        val currency: Currency,
        val term: Term,
        val rate: Double
) : MarketDataElement {

    @Id
    @GeneratedValue
    val id: Long? = null

    override fun businessKey() = MultiKey(indexName, currency, term)
}
