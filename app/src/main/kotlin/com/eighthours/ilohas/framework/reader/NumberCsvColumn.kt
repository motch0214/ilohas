package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.framework.validation.Violation
import org.apache.commons.csv.CSVRecord
import kotlin.reflect.KMutableProperty1


class DoubleColumn<T>(header: String, property: KMutableProperty1<T, in Double>, isMandatory: Boolean)
    : AbstractCsvColumn<T, Double>(header, property, isMandatory) {

    override fun convert(string: String, record: CSVRecord): Which<Double, Violation> {
        val number = string.toDoubleOrNull()
                ?: return Which.right(FormatViolation(header, record.recordNumber, string))
        return Which.left(number)
    }
}


fun <T> StringColumnBuilder<T>.double() = DoubleColumnBuilder(columns, header)

class DoubleColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, Double?>(columns, header) {

    override fun build(property: KMutableProperty1<T, Double?>) = DoubleColumn(header, property, true)
}

fun <T> MandatoryStringColumnBuilder<T>.double() = MandatoryDoubleColumnBuilder(columns, header)

class MandatoryDoubleColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, Double>(columns, header) {

    override fun build(property: KMutableProperty1<T, Double>) = DoubleColumn(header, property, false)
}
