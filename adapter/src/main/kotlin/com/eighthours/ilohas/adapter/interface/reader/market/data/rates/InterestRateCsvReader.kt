package com.eighthours.ilohas.adapter.`interface`.reader.market.data.rates

import com.eighthours.ilohas.app.`interface`.InterfaceReader
import com.eighthours.ilohas.app.`interface`.market.data.rates.InterestRateDto
import com.eighthours.ilohas.framework.reader.CsvReader
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Component


@Component
class InterestRateCsvReader : CsvReader<InterestRateDto>(), InterfaceReader<InterestRateDto> {

    override val format: CSVFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader()

    override fun CSVRecord.parse() = InterestRateDto(
            currency = this["Currency"],
            indexName = this["IndexName"],
            term = this["Term"],
            rate = this["Rate"])
}
