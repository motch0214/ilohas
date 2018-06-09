package com.eighthours.ilohas.framework.validation

import com.eighthours.ilohas.domain.system.LogMessage


interface Violation {
    val level: ViolationLevel
    fun toMessage(): LogMessage
}


enum class ViolationLevel {
    ERROR, WARN
}


class ValidationResults {

    private val mutableViolations = mutableListOf<Violation>()

    val violations: List<Violation> get() = mutableViolations

    var hasError = false
        private set

    val isNotEmpty: Boolean get() = violations.isNotEmpty()

    fun add(violation: Violation) {
        mutableViolations.add(violation)
        if (violation.level == ViolationLevel.ERROR) {
            hasError = true
        }
    }

    fun merge(other: ValidationResults) {
        mutableViolations.addAll(other.violations)
        if (other.hasError) {
            hasError = true
        }
    }
}
