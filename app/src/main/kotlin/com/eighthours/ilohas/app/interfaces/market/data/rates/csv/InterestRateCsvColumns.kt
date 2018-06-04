package com.eighthours.ilohas.app.interfaces.market.data.rates.csv

import com.eighthours.ilohas.app.interfaces.column.currency
import com.eighthours.ilohas.app.interfaces.column.term
import com.eighthours.ilohas.framework.reader.CsvColumns
import com.eighthours.ilohas.framework.reader.column.double
import com.eighthours.ilohas.framework.reader.column.mandatory


object InterestRateCsvColumns : CsvColumns<InterestRateCsvObject>() {

    val currency = column("Currency").mandatory().currency().bind(InterestRateCsvObject::currency)

    val indexName = column("IndexName").mandatory().bind(InterestRateCsvObject::indexName)

    val term = column("Term").mandatory().term().bind(InterestRateCsvObject::term)

    val rate = column("Rate").mandatory().double().bind(InterestRateCsvObject::rate)
}
