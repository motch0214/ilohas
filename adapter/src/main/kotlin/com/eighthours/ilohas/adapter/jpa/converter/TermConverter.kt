package com.eighthours.ilohas.adapter.jpa.converter

import com.eighthours.ilohas.domain.market.Term
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class TermConverter : AttributeConverter<Term, String> {

    override fun convertToDatabaseColumn(attribute: Term?): String? {
        return attribute?.code
    }

    override fun convertToEntityAttribute(dbData: String?): Term? {
        return dbData?.let { Term.of(it) }
    }
}
