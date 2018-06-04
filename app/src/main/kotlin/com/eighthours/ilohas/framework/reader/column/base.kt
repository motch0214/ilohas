package com.eighthours.ilohas.framework.reader.column

import com.eighthours.ilohas.framework.Result
import com.eighthours.ilohas.framework.err
import com.eighthours.ilohas.framework.reader.CsvColumn
import com.eighthours.ilohas.framework.reader.CsvColumns
import com.eighthours.ilohas.framework.reader.CsvObject
import com.eighthours.ilohas.framework.validation.Violation
import kotlin.reflect.KMutableProperty1


private class CsvColumnImpl<T : CsvObject, V>(
        override val header: String,
        private val property: KMutableProperty1<T, V>,
        private val builder: CsvColumnMapping<T, V>) : CsvColumn<T> {

    override fun set(receiver: T, string: String?): Violation? {
        val (value, violation) = builder.map(receiver, string)

        if (violation != null) {
            return violation
        }
        if (value != null) {
            property.set(receiver, value)
        }
        return null
    }
}


interface CsvColumnBinding<T : CsvObject> {
    val columns: CsvColumns<T>
    val header: String
}

interface CsvColumnMapping<T : CsvObject, V> {
    fun map(obj: T, string: String?): Result<V, Violation>
}

interface CsvColumnBuilder<T : CsvObject, V> : CsvColumnBinding<T>, CsvColumnMapping<T, V> {
    fun bind(property: KMutableProperty1<T, V>): CsvColumn<T> = CsvColumnImpl(header, property, this).also { columns.columnList.add(it) }
}


fun <V : Any> V.ok(): Result<V, Violation> = com.eighthours.ilohas.framework.ok(this)


private class ColumnBuilderImpl<T : CsvObject, S, V>(
        private val previous: CsvColumnBuilder<T, S>,
        private val mapper: (T, S) -> Result<V, Violation>)
    : CsvColumnBinding<T> by previous, CsvColumnBuilder<T, V> {

    override fun map(obj: T, string: String?): Result<V, Violation> {
        val s = previous.map(obj, string).ifError { return err(it) }
        return mapper(obj, s)
    }
}

fun <T : CsvObject, S, V> CsvColumnBuilder<T, S>.builder(mapper: (T, S) -> Result<V, Violation>)
        : CsvColumnBuilder<T, V> = ColumnBuilderImpl(this, mapper)
