package com.eighthours.ilohas.app.interfaces.market.data.rates.csv

import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.domain.market.Term
import com.eighthours.ilohas.framework.reader.CsvObject


class InterestRateCsvObject : CsvObject {

    override var lineNumber: Long = -1

    lateinit var currency: Currency

    lateinit var indexName: String

    lateinit var term: Term

    var rate: Double = 0.0
}
