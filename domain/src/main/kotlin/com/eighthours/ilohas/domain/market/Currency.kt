package com.eighthours.ilohas.domain.market


data class Currency(val code: String) {

    companion object {
        fun of(code: String) = Currency(code)
    }
}


data class CurrencyPair(val left: Currency, val right: Currency) {

    companion object {
        fun of(code: String): CurrencyPair {
            val pair = code.split('/')
            return CurrencyPair(Currency.of(pair[0]), Currency.of(pair[1]))
        }
    }
}
