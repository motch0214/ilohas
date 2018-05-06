package com.eighthours.ilohas.app.`interface`.market.data

import com.eighthours.ilohas.config.AppConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.nio.file.FileSystems
import java.nio.file.Path
import java.time.LocalDate
import javax.inject.Inject


@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [AppConfig::class])
@Transactional
class MarketDataImportUsecaseTest {

    @Inject
    private lateinit var usecase: MarketDataImportUsecase

    protected fun testDataDirectory(): Path {
        return FileSystems.getDefault().getPath("src/test/data", this::class.java.simpleName)
    }

    @Test
    fun test() {
        usecase.import("2018-04-01".toDate(), testDataDirectory())
    }

    private fun String.toDate(): LocalDate {
        return LocalDate.parse(this)
    }
}
