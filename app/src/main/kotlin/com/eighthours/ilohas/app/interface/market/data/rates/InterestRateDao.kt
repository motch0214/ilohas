package com.eighthours.ilohas.app.`interface`.market.data.rates

import com.eighthours.ilohas.domain.market.data.rates.InterestRate


interface InterestRateDao {

    fun saveAll(rates: Collection<InterestRate>)
}
