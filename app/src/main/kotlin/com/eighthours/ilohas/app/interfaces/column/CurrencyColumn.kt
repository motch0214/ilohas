package com.eighthours.ilohas.app.interfaces.column

import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.framework.ok
import com.eighthours.ilohas.framework.reader.CsvObject
import com.eighthours.ilohas.framework.reader.column.MandatoryStringColumnBuilder
import com.eighthours.ilohas.framework.reader.column.builder


fun <T : CsvObject> MandatoryStringColumnBuilder<T>.currency() = builder<T, String, Currency> { obj, s ->
    ok(Currency.of(s))
}
