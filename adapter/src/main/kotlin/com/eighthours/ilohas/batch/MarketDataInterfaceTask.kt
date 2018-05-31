package com.eighthours.ilohas.batch

import com.eighthours.ilohas.app.interfaces.market.data.MarketDataImportUsecase
import org.springframework.stereotype.Component
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@Component("MarketDataInterfaceTask")
class MarketDataInterfaceTask : BatchTask {

    @Inject
    private lateinit var usecase: MarketDataImportUsecase

    override fun execute(args: Array<String>) {
        val baseDate = LocalDate.parse(args[0], DateTimeFormatter.BASIC_ISO_DATE)
        val targetDirectory = Paths.get(args[1])
        usecase.import(baseDate, targetDirectory)
    }
}
