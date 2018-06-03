package com.eighthours.ilohas.app.interfaces

import com.eighthours.ilohas.framework.validation.ValidationResults
import com.eighthours.ilohas.framework.validation.Violation
import com.eighthours.ilohas.framework.validation.ViolationType
import org.apache.commons.collections4.keyvalue.MultiKey


class RecordViolation(val delegate: ValidationResults) : Violation {

    override val type = if (delegate.hasError) ViolationType.ERROR else ViolationType.WARN
}

class DuplicationViolation(val businessKey: MultiKey<*>) : Violation {

    override val type = ViolationType.WARN
}
