package com.eighthours.ilohas.app.interfaces.converter

import com.eighthours.ilohas.domain.market.Term
import com.eighthours.ilohas.framework.reader.*
import kotlin.reflect.KMutableProperty1


class TermColumn<T>(header: String, property: KMutableProperty1<T, in Term>, isMandatory: Boolean)
    : AbstractCsvColumn<T, Term>(header, property, isMandatory) {

    override fun convert(string: String): Which<Term, Violation> {
        return try {
            Which.left(Term.of(string))
        } catch (e: IllegalArgumentException) {
            Which.right(FormatViolation(header, string))
        }
    }
}


fun <T> StringColumnBuilder<T>.term() = TermColumnBuilder(columns, header)

class TermColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, Term?>(columns, header) {

    override fun build(property: KMutableProperty1<T, Term?>) = TermColumn(header, property, false)
}

fun <T> MandatoryStringColumnBuilder<T>.term() = MandatoryTermColumnBuilder(columns, header)

class MandatoryTermColumnBuilder<T>(columns: CsvColumns<T>, header: String) : CsvColumnBuilder<T, Term>(columns, header) {

    override fun build(property: KMutableProperty1<T, Term>) = TermColumn(header, property, true)
}
