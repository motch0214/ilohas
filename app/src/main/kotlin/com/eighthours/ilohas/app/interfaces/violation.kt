package com.eighthours.ilohas.app.interfaces

import com.eighthours.ilohas.domain.system.LogMessage
import com.eighthours.ilohas.framework.validation.Violation
import com.eighthours.ilohas.framework.validation.ViolationLevel


class DuplicationViolation(val businessKey: List<String>) : Violation {

    override val level = ViolationLevel.ERROR

    override fun toMessage() = LogMessage("violation.duplication", "[${businessKey.joinToString(", ")}]")
}
