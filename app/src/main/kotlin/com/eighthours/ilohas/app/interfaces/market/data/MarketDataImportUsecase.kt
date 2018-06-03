package com.eighthours.ilohas.app.interfaces.market.data

import com.eighthours.ilohas.app.interfaces.DuplicationViolation
import com.eighthours.ilohas.app.interfaces.RecordViolation
import com.eighthours.ilohas.app.interfaces.market.data.rates.InterestRateImporterFactory
import com.eighthours.ilohas.domain.market.data.MarketData
import com.eighthours.ilohas.domain.market.data.MarketDataRepository
import com.eighthours.ilohas.domain.system.SystemLog
import com.eighthours.ilohas.domain.system.SystemLogRepository
import com.eighthours.ilohas.framework.reader.FormatViolation
import com.eighthours.ilohas.framework.reader.MandatoryViolation
import com.eighthours.ilohas.framework.validation.Violation
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
    private lateinit var systemLogRepository: SystemLogRepository

    @Inject
    private lateinit var interestRateImporterFactory: InterestRateImporterFactory

    fun import(baseDate: LocalDate, targetDirectory: Path) {
        val marketData = marketDataRepository.save(MarketData(marketDataName, baseDate))

        val importer = interestRateImporterFactory.create(marketData.id!!, targetDirectory)
        importer.import()

        val path = importer.targetFile.toString()
        val logs = importer.validationResults.violations.flatMap { v -> translate(v, path) }
        systemLogRepository.saveAll(logs)
    }

    private fun translate(v: Violation, path: String): List<SystemLog> {
        return when (v) {
            is RecordViolation -> v.delegate.violations.map { translateDetails(v, path) }
            is DuplicationViolation -> listOf(SystemLog.warn("interface.duplication", path, v.businessKey.keys.toString()))
            else -> throw IllegalArgumentException("unknown violation: $v")
        }
    }

    private fun translateDetails(v: Violation, path: String): SystemLog {
        return when (v) {
            is MandatoryViolation -> SystemLog.warn("interface.mandatory", path, v.header)
            is FormatViolation -> SystemLog.warn("interface.format", path, v.header, v.value)
            else -> throw IllegalArgumentException("unknown violation: $v")
        }
    }
}
