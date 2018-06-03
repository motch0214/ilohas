package com.eighthours.ilohas.domain.market.data

import org.apache.commons.collections4.keyvalue.MultiKey


interface MarketDataElement {

    fun businessKey(): MultiKey<*>
}
