package com.eighthours.ilohas.app.interfaces.market.data.rates.csv

import com.eighthours.ilohas.framework.reader.CsvColumns


object InterestRateCsvColumns : CsvColumns<InterestRateCsvObject>() {

    val currency = column("Currency", InterestRateCsvObject::currency)

    val indexName = column("IndexName", InterestRateCsvObject::indexName)

    val term = column("Term", InterestRateCsvObject::term)

    val rate = column("Rate", InterestRateCsvObject::rate)
}
