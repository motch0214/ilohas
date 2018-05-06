package com.eighthours.ilohas.domain.market.data

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


typealias MarketDataId = Long

@Entity
class MarketData(
        val name: String,
        val baseDate: LocalDate
) {
    @Id
    @GeneratedValue
    val id: MarketDataId? = null
}
