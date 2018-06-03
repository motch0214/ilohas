package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.framework.validation.Violation
import org.apache.commons.csv.CSVRecord
import kotlin.reflect.KMutableProperty1


interface CsvColumn<T> {
    val header: String
    fun set(receiver: T, record: CSVRecord): Violation?
}


abstract class CsvColumns<T> {

    internal val columns = mutableListOf<CsvColumn<T>>()

    protected fun column(header: String) = StringColumnBuilder<T>(this, header)
}


abstract class AbstractCsvColumn<T, V : Any>(override val header: String, private val property: KMutableProperty1<T, in V>,
                                             private val isMandatory: Boolean) : CsvColumn<T> {

    override fun set(receiver: T, record: CSVRecord): Violation? {
        val string = record[header]
        if (string == null || string.isBlank())
            return if (isMandatory) MandatoryViolation(header, record.recordNumber) else null

        val (converted, violation) = convert(string, record)

        if (violation != null) return violation

        property.set(receiver, converted!!)
        return null
    }

    abstract fun convert(string: String, record: CSVRecord): Which<V, Violation>
}


class StringColumn<T>(header: String, property: KMutableProperty1<T, in String>, isMandatory: Boolean)
    : AbstractCsvColumn<T, String>(header, property, isMandatory) {

    override fun convert(string: String, record: CSVRecord): Which<String, Violation> = Which.left(string)
}


private class IgnoredColumn<T>(override val header: String) : CsvColumn<T> {

    override fun set(receiver: T, record: CSVRecord): Nothing? = null
}


abstract class CsvColumnBuilder<T, V>(val columns: CsvColumns<T>, val header: String) {

    fun bind(property: KMutableProperty1<T, V>) = build(property).also { columns.columns.add(it) }

    protected abstract fun build(property: KMutableProperty1<T, V>): CsvColumn<T>
}


class StringColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, String?>(columns, header) {

    override fun build(property: KMutableProperty1<T, String?>) = StringColumn(header, property, false)

    fun ignore(): CsvColumn<T> = IgnoredColumn<T>(header).also { columns.columns.add(it) }

    fun mandatory() = MandatoryStringColumnBuilder(columns, header)
}

class MandatoryStringColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, String>(columns, header) {

    override fun build(property: KMutableProperty1<T, String>) = StringColumn(header, property, true)
}
