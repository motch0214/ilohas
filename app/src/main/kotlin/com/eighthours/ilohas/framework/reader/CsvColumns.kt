package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.framework.reader.column.StringColumnBuilder
import com.eighthours.ilohas.framework.validation.Violation


interface CsvObject {
    var lineNumber: Long
}

interface CsvColumn<T : CsvObject> {
    val header: String
    fun set(receiver: T, string: String?): Violation?
}


abstract class CsvColumns<T : CsvObject> {

    internal val columnList = mutableListOf<CsvColumn<T>>()

    protected fun column(header: String) = StringColumnBuilder(this, header)

    protected fun headers(): Array<String> = columnList.map { it.header }.toTypedArray()
}
