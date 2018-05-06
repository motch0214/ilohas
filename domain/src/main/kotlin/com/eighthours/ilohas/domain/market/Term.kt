package com.eighthours.ilohas.domain.market


sealed class Term {

    companion object {
        fun of(code: String): Term {
            return when {
                code == ONTerm.code -> ONTerm
                code == TNTerm.code -> TNTerm
                code == SNTerm.code -> SNTerm
                code.endsWith('W') -> WeekTerm(code.parseNumber())
                code.endsWith('M') -> MonthTerm(code.parseNumber())
                code.endsWith('Y') -> YearTerm(code.parseNumber())
                else -> throw IllegalArgumentException("unexpected term: $code")
            }
        }
    }

    abstract val code: String
}


object ONTerm : Term() {
    override val code = "O/N"
}

object TNTerm : Term() {
    override val code = "T/N"
}

object SNTerm : Term() {
    override val code = "S/N"
}

data class WeekTerm(val weeks: Int) : Term() {
    override val code = "${weeks}W"
}

data class MonthTerm(val months: Int) : Term() {
    override val code = "${months}M"
}

data class YearTerm(val years: Int) : Term() {
    override val code = "${years}Y"
}


private fun String.parseNumber(): Int {
    return substring(0 until (length - 1)).toInt()
}
