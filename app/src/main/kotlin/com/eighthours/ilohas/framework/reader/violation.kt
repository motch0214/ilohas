package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.framework.validation.Violation
import com.eighthours.ilohas.framework.validation.ViolationType


abstract class CsvViolation(val header: String, val lineNumber: Long) : Violation {

    override val type = ViolationType.ERROR
}

class MandatoryViolation(header: String, lineNumber: Long) : CsvViolation(header, lineNumber)

class FormatViolation(header: String, lineNumber: Long, val value: String) : CsvViolation(header, lineNumber)
