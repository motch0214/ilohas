package com.eighthours.ilohas.app.interfaces.market.data.rates.csv


data class InterestRateCsvObject(

        var currency: String? = null,

        var indexName: String? = null,

        var term: String? = null,

        var rate: String? = null
)
