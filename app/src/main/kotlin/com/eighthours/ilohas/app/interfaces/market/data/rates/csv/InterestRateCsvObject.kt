package com.eighthours.ilohas.app.interfaces.market.data.rates.csv

import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.domain.market.Term


class InterestRateCsvObject {

    lateinit var currency: Currency

    lateinit var indexName: String

    lateinit var term: Term

    var rate: Double = 0.0
}
