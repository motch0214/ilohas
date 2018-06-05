package com.eighthours.ilohas.app.interfaces.market.data

import com.eighthours.ilohas.app.interfaces.market.data.rates.InterestRateImporterFactory
import com.eighthours.ilohas.app.interfaces.market.data.rates.InterestRateImporterFactory.InterestRateImporter
import com.eighthours.ilohas.domain.market.data.MarketData
import com.eighthours.ilohas.domain.market.data.MarketDataRepository
import com.eighthours.ilohas.domain.system.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Path
import java.time.LocalDate
import java.time.ZonedDateTime
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

        val datetime = ZonedDateTime.now()
        if (importer.validationResults.isNotEmpty) {
            systemLogRepository.save(warningLog(importer, datetime))
        }
        systemLogRepository.save(importedLog(importer, datetime))
    }

    private fun warningLog(importer: InterestRateImporter, datetime: ZonedDateTime): SystemLog {
        val details = importer.validationResults.violations
                .map { it.toMessage() }
                .map { SystemLogDetails(it) }
        val summary = warningSummaryLogMessage(importer.targetFile.toFile().absolutePath,
                "item.market.data.dataType.${importer.dataType}", "${importer.skippedCount}")
        return SystemLog(SystemLogType.WARN, SystemLogCategory.INTERFACE, summary, details, datetime)
    }

    private fun importedLog(importer: InterestRateImporter, datetime: ZonedDateTime): SystemLog {
        val message = importedLogMessage(importer.targetFile.toFile().absolutePath,
                "item.market.data.dataType.${importer.dataType}", "${importer.importedCount}")
        return SystemLog(SystemLogType.INFO, SystemLogCategory.INTERFACE, message, emptyList(), datetime)
    }
}


private fun warningSummaryLogMessage(file: String, dataTypeId: String, skippedCount: String) =
        LogMessage("message.systemLog.interface.market.data.warning.summary", file, dataTypeId, skippedCount)

private fun importedLogMessage(file: String, dataTypeId: String, importedCount: String) =
        LogMessage("message.systemLog.interface.market.data.imported", file, dataTypeId, importedCount)
