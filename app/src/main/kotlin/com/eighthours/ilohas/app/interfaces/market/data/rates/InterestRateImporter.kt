package com.eighthours.ilohas.app.interfaces.market.data.rates

import com.eighthours.ilohas.app.interfaces.DuplicationViolation
import com.eighthours.ilohas.app.interfaces.InterfaceReader
import com.eighthours.ilohas.app.interfaces.market.data.rates.csv.InterestRateCsvObject
import com.eighthours.ilohas.domain.market.data.MarketDataId
import com.eighthours.ilohas.domain.market.data.rates.InterestRate
import com.eighthours.ilohas.framework.validation.ValidationResults
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.file.Path
import javax.inject.Inject


@Component
class InterestRateImporterFactory {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

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
            targetDirectory: Path) {

        val dataType = "InterestRate"

        val targetFile: Path = targetDirectory.resolve(fileName)

        val validationResults = ValidationResults()

        private val importedBusinessKeys = mutableSetOf<List<String>>()

        val importedCount: Int get() = importedBusinessKeys.size

        var skippedCount: Int = 0
            private set

        fun import() {
            log.debug("Import starts.")
            reader.read(targetFile) { rates ->
                rates.filter { (_, results) -> resolve(results) }
                        .map { (rate, _) -> translate(rate) }
                        .filter(::checkDuplication)
                        .chunked(chunkSize)
                        .forEach(dao::saveAll)
            }
            log.debug("Import finishes.")
        }

        private fun resolve(results: ValidationResults): Boolean {
            validationResults.merge(results)
            return if (results.hasError) {
                skippedCount++
                false
            } else {
                true
            }
        }

        private fun translate(obj: InterestRateCsvObject): InterestRate {
            return InterestRate(
                    marketDataId = marketDataId,
                    indexName = obj.indexName,
                    currency = obj.currency,
                    term = obj.term,
                    rate = obj.rate)
        }

        private fun checkDuplication(rate: InterestRate): Boolean {
            val key = rate.businessKey()
            val added = importedBusinessKeys.add(key)
            if (!added) {
                validationResults.add(DuplicationViolation(key))
                skippedCount++
            }
            return added
        }
    }
}
