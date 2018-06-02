package com.eighthours.ilohas.app.interfaces.market.data.rates

import com.eighthours.ilohas.app.interfaces.InterfaceReader
import com.eighthours.ilohas.app.interfaces.market.data.rates.csv.InterestRateCsvColumns
import com.eighthours.ilohas.app.interfaces.market.data.rates.csv.InterestRateCsvObject
import com.eighthours.ilohas.framework.reader.CsvReader
import org.apache.commons.csv.CSVFormat


object InterestRateCsvReader : CsvReader<InterestRateCsvObject>(), InterfaceReader<InterestRateCsvObject> {

    override val columns = InterestRateCsvColumns

    override val format: CSVFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader()

    override fun createObj() = InterestRateCsvObject()
}
