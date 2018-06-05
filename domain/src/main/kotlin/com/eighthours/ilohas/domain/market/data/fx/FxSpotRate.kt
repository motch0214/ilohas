package com.eighthours.ilohas.domain.market.data.fx

import com.eighthours.ilohas.domain.market.CurrencyPair
import com.eighthours.ilohas.domain.market.data.MarketDataElement
import com.eighthours.ilohas.domain.market.data.MarketDataId
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class FxSpotRate(
        val marketDataId: MarketDataId,
        val currencyPair: CurrencyPair,
        val rate: Double
) : MarketDataElement {

    @Id
    @GeneratedValue
    val id: Long? = null

    override fun businessKey() = listOf(currencyPair.code)
}
