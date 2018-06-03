package com.eighthours.ilohas.app.interfaces.market.data.rates

import com.eighthours.ilohas.app.interfaces.InterfaceReader
import com.eighthours.ilohas.app.interfaces.market.data.rates.csv.InterestRateCsvObject
import com.eighthours.ilohas.domain.market.data.MarketDataId
import com.eighthours.ilohas.domain.market.data.rates.InterestRate
import com.eighthours.ilohas.framework.reader.ValidationResults
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
    private lateinit var dao: InterestRateDao

    private val reader: InterfaceReader<InterestRateCsvObject> = InterestRateCsvReader

    fun create(marketDataId: MarketDataId, targetDirectory: Path) =
            InterestRateImporter(marketDataId, targetDirectory)

    inner class InterestRateImporter(
            private val marketDataId: MarketDataId,
            private val targetDirectory: Path) {

        private val validationResults = ValidationResults()

        fun import() {
            reader.read(targetDirectory.resolve(fileName)) { rates ->
                rates.filter { (_, results) -> resolve(results) }
                        .map { (rate, _) -> convert(rate) }
                        .chunked(chunkSize)
                        .forEach { dao.saveAll(it) }
            }
        }

        private fun resolve(results: ValidationResults): Boolean {
            validationResults.merge(results)
            return !results.hasError
        }

        private fun convert(obj: InterestRateCsvObject): InterestRate {
            return InterestRate(
                    marketDataId = marketDataId,
                    indexName = obj.indexName,
                    currency = obj.currency,
                    term = obj.term,
                    rate = obj.rate)
        }
    }
}
