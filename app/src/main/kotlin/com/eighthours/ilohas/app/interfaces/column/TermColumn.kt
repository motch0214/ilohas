package com.eighthours.ilohas.app.interfaces.column

import com.eighthours.ilohas.domain.market.Term
import com.eighthours.ilohas.framework.err
import com.eighthours.ilohas.framework.reader.CsvObject
import com.eighthours.ilohas.framework.reader.FormatViolation
import com.eighthours.ilohas.framework.reader.column.MandatoryStringColumnBuilder
import com.eighthours.ilohas.framework.reader.column.builder
import com.eighthours.ilohas.framework.reader.column.ok


fun <T : CsvObject> MandatoryStringColumnBuilder<T>.term() = builder<T, String, Term> { obj, s ->
    try {
        Term.of(s).ok()
    } catch (e: IllegalArgumentException) {
        err(FormatViolation(header, obj.lineNumber, s))
    }
}
