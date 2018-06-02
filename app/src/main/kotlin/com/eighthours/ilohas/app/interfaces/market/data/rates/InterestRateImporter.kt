package com.eighthours.ilohas.app.interfaces.market.data.rates

import com.eighthours.ilohas.app.interfaces.InterfaceReader
import com.eighthours.ilohas.domain.market.Currency
import com.eighthours.ilohas.domain.market.Term
import com.eighthours.ilohas.domain.market.data.MarketDataId
import com.eighthours.ilohas.domain.market.data.rates.InterestRate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.file.Path
import javax.inject.Inject


@Component
class InterestRateImporterFactory {

    @Value("\${ilohas.import.market.interestRate.fileName}")
    private lateinit var fileName: String

    @Value("\${ilohas.import.chunkSize}")
    private val chunkSize: Int = 100

    @Inject
    private lateinit var reader: InterfaceReader<InterestRateDto>

    @Inject
    private lateinit var dao: InterestRateDao

    fun create(marketDataId: MarketDataId, targetDirectory: Path) =
            InterestRateImporter(marketDataId, targetDirectory)

    inner class InterestRateImporter(
            val marketDataId: MarketDataId,
            val targetDirectory: Path) {

        fun import() {
            reader.read(targetDirectory.resolve(fileName)) { rates ->
                rates.map(::convert).chunked(chunkSize).forEach { dao.saveAll(it) }
            }
        }

        private fun convert(dto: InterestRateDto): InterestRate {
            return InterestRate(
                    marketDataId = marketDataId,
                    indexName = dto.indexName!!,
                    currency = Currency.of(dto.currency!!),
                    term = Term.of(dto.term!!),
                    rate = dto.rate?.toDouble()!!)
        }
    }
}


data class InterestRateDto(
        val currency: String?,
        val indexName: String?,
        val term: String?,
        val rate: String?
)
