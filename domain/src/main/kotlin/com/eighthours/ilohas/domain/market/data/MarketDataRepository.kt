package com.eighthours.ilohas.domain.market.data


interface MarketDataRepository {

    fun save(marketData: MarketData): MarketData
}
