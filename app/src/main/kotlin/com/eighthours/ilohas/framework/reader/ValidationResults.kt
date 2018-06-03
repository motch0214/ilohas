package com.eighthours.ilohas.framework.reader


abstract class Violation(val type: ViolationType, val header: String)


enum class ViolationType {
    ERROR, WARN
}


class ValidationResults {

    private val mutableViolations = mutableListOf<Violation>()

    val violations: List<Violation> get() = mutableViolations

    var hasError = false
        private set

    fun add(violation: Violation) {
        mutableViolations.add(violation)
        if (violation.type == ViolationType.ERROR) {
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
