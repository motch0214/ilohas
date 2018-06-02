package com.eighthours.ilohas.app.`interface`.market.data

import com.eighthours.ilohas.app.`interface`.market.data.rates.InterestRateImporterFactory
import com.eighthours.ilohas.domain.market.data.MarketData
import com.eighthours.ilohas.domain.market.data.MarketDataRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Path
import java.time.LocalDate
import javax.inject.Inject


@Component
@Transactional
class MarketDataImportUsecase {

    @Value("\${ilohas.import.market.marketDataName}")
    private lateinit var marketDataName: String

    @Inject
    private lateinit var marketDataRepository: MarketDataRepository

    @Inject
    private lateinit var interestRateImporterFactory: InterestRateImporterFactory

    fun import(baseDate: LocalDate, targetDirectory: Path) {
        val marketData = marketDataRepository.save(MarketData(marketDataName, baseDate))

        val importer = interestRateImporterFactory.create(marketData.id!!, targetDirectory)
        importer.import()
    }
}
