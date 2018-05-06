package com.eighthours.ilohas.adapter.jpa.market.data

import com.eighthours.ilohas.domain.market.data.MarketData
import com.eighthours.ilohas.domain.market.data.MarketDataId
import com.eighthours.ilohas.domain.market.data.MarketDataRepository
import org.springframework.data.jpa.repository.JpaRepository


interface MarketDataJpaRepository : MarketDataRepository, JpaRepository<MarketData, MarketDataId>
