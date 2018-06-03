package com.eighthours.ilohas.framework.reader


class MandatoryViolation(header: String) : Violation(ViolationType.ERROR, header)

class FormatViolation(header: String, val value: String) : Violation(ViolationType.ERROR, header)
