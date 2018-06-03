package com.eighthours.ilohas.app.interfaces

import com.eighthours.ilohas.framework.validation.Violation
import com.eighthours.ilohas.framework.validation.ViolationType
import org.apache.commons.collections4.keyvalue.MultiKey


class DuplicationViolation(val businessKey: MultiKey<*>) : Violation {

    override val type = ViolationType.WARN
}
