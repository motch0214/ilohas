package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.domain.system.LogMessage
import com.eighthours.ilohas.framework.reader.column.BoundaryType
import com.eighthours.ilohas.framework.validation.Violation
import com.eighthours.ilohas.framework.validation.ViolationType


abstract class CsvViolation(val header: String, val lineNumber: Long) : Violation {

    override val type = ViolationType.ERROR
}

class MandatoryViolation(header: String, lineNumber: Long) : CsvViolation(header, lineNumber) {

    override fun toMessage() = LogMessage("violation.mandatory", header, "$lineNumber")
}

class FormatViolation(header: String, lineNumber: Long, val value: String) : CsvViolation(header, lineNumber) {

    override fun toMessage() = LogMessage("violation.format", header, "$lineNumber", value)
}

class BoundaryViolation(header: String, lineNumber: Long, val value: Number,
                        val boundaryType: BoundaryType, val boundary: Number) : CsvViolation(header, lineNumber) {

    override fun toMessage() = LogMessage("violation.number.boundary.${boundaryType.name}",
            header, "$lineNumber", "$value", "$boundary")
}
