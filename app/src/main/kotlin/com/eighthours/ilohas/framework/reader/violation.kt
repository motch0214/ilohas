package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.framework.validation.Violation
import com.eighthours.ilohas.framework.validation.ViolationType


abstract class CsvViolation(override val type: ViolationType, val header: String) : Violation

class MandatoryViolation(header: String) : CsvViolation(ViolationType.ERROR, header)

class FormatViolation(header: String, val value: String) : CsvViolation(ViolationType.ERROR, header)
