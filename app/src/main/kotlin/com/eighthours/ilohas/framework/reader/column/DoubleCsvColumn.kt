package com.eighthours.ilohas.framework.reader.column

import com.eighthours.ilohas.framework.err
import com.eighthours.ilohas.framework.ok
import com.eighthours.ilohas.framework.reader.BoundaryViolation
import com.eighthours.ilohas.framework.reader.CsvObject
import com.eighthours.ilohas.framework.reader.FormatViolation
import com.eighthours.ilohas.framework.validation.Violation


fun <T : CsvObject> StringColumnBuilder<T>.double() = builder { obj, s ->
    return@builder if (s == null) {
        ok<Double?, Violation>(null)
    } else {
        s.toDoubleOrNull()?.ok() ?: err(FormatViolation(header, obj.lineNumber, s))
    }
}

fun <T : CsvObject> MandatoryStringColumnBuilder<T>.double() = builder { obj, s ->
    return@builder s.toDoubleOrNull()?.ok() ?: err(FormatViolation(header, obj.lineNumber, s))
}


fun <T : CsvObject, V> CsvColumnBuilder<T, V>.bound(type: BoundaryType, boundary: V)
        where V : Comparable<V>, V : Number = builder { obj, s ->
    fun violate() = err<V, Violation>(BoundaryViolation(header, obj.lineNumber, s, type, boundary))

    when (type) {
        BoundaryType.LT -> if (s >= boundary) return@builder violate()
        BoundaryType.LE -> if (s > boundary) return@builder violate()
        BoundaryType.GT -> if (s <= boundary) return@builder violate()
        BoundaryType.GE -> if (s < boundary) return@builder violate()
    }
    ok<V, Violation>(s)
}


enum class BoundaryType {
    /** less than */
    LT,
    /** less than or equal to */
    LE,
    /** greater than */
    GT,
    /** greater than or equal to */
    GE;
}
