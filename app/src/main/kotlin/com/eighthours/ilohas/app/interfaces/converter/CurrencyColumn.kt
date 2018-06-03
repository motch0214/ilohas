package com.eighthours.ilohas.app.interfaces.converter

import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.framework.reader.*
import com.eighthours.ilohas.framework.validation.Violation
import org.apache.commons.csv.CSVRecord
import kotlin.reflect.KMutableProperty1


class CurrencyColumn<T>(header: String, property: KMutableProperty1<T, in Currency>, isMandatory: Boolean)
    : AbstractCsvColumn<T, Currency>(header, property, isMandatory) {

    override fun convert(string: String, record: CSVRecord): Which<Currency, Violation> {
        return Which.left(Currency.of(string))
    }
}


fun <T> StringColumnBuilder<T>.currency() = CurrencyColumnBuilder(columns, header)

class CurrencyColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, Currency?>(columns, header) {

    override fun build(property: KMutableProperty1<T, Currency?>) = CurrencyColumn(header, property, false)
}

fun <T> MandatoryStringColumnBuilder<T>.currency() = MandatoryCurrencyColumnBuilder(columns, header)

class MandatoryCurrencyColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, Currency>(columns, header) {

    override fun build(property: KMutableProperty1<T, Currency>) = CurrencyColumn(header, property, true)
}
