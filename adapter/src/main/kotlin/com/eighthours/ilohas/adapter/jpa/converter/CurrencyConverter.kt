package com.eighthours.ilohas.adapter.jpa.converter

import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.domain.market.CurrencyPair
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class CurrencyConverter : AttributeConverter<Currency, String> {

    override fun convertToDatabaseColumn(attribute: Currency?): String? {
        return attribute?.code
    }

    override fun convertToEntityAttribute(dbData: String?): Currency? {
        return dbData?.let { Currency.of(it) }
    }
}


@Converter(autoApply = true)
class CurrencyPairConverter : AttributeConverter<CurrencyPair, String> {

    override fun convertToDatabaseColumn(attribute: CurrencyPair?): String? {
        return attribute?.code
    }

    override fun convertToEntityAttribute(dbData: String?): CurrencyPair? {
        return dbData?.let { CurrencyPair.of(it) }
    }
}
