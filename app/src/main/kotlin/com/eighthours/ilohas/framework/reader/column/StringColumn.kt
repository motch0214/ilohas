package com.eighthours.ilohas.framework.reader.column

import com.eighthours.ilohas.framework.Result
import com.eighthours.ilohas.framework.err
import com.eighthours.ilohas.framework.ok
import com.eighthours.ilohas.framework.reader.CsvColumn
import com.eighthours.ilohas.framework.reader.CsvColumns
import com.eighthours.ilohas.framework.reader.CsvObject
import com.eighthours.ilohas.framework.reader.MandatoryViolation
import com.eighthours.ilohas.framework.validation.Violation


class StringColumnBuilder<T : CsvObject>(override val columns: CsvColumns<T>, override val header: String) : CsvColumnBuilder<T, String?> {

    override fun map(obj: T, string: String?): Result<String?, Violation> {
        if (string == null || string.isBlank())
            return ok(null)
        return ok(string)
    }

    fun ignore(): CsvColumn<T> = IgnoreColumn<T>(header).also { columns.columnList.add(it) }
}


private class IgnoreColumn<T : CsvObject>(override val header: String) : CsvColumn<T> {

    override fun set(receiver: T, string: String?): Violation? {
        return null
    }
}


fun <T : CsvObject> StringColumnBuilder<T>.mandatory() = MandatoryStringColumnBuilder(this)

class MandatoryStringColumnBuilder<T : CsvObject>(private val previous: CsvColumnBuilder<T, String?>)
    : CsvColumnBinding<T> by previous, CsvColumnBuilder<T, String> {

    override fun map(obj: T, string: String?): Result<String, Violation> {
        val s = previous.map(obj, string).ifError { return err(it) }
                ?: return err(MandatoryViolation(header, obj.lineNumber))
        return ok(s)
    }
}
