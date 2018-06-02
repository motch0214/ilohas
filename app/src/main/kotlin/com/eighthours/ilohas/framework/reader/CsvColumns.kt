package com.eighthours.ilohas.framework.reader

import kotlin.reflect.KMutableProperty1


abstract class CsvColumns<T> {

    internal val columns = mutableListOf<CsvColumn<T>>()

    protected fun column(header: String, property: KMutableProperty1<T, String?>? = null): CsvColumn<T> {
        val col = CsvColumn(header, property)
        columns.add(col)
        return col
    }
}


class CsvColumn<T>(
        val header: String,
        val property: KMutableProperty1<T, String?>?
)
