package com.eighthours.ilohas.app.interfaces.market.data

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.nio.file.FileSystems
import java.nio.file.Path
import java.time.LocalDate
import javax.inject.Inject


@SpringBootTest
@RunWith(SpringRunner::class)
@Transactional
class MarketDataImportUsecaseTest {

    @Inject
    private lateinit var usecase: MarketDataImportUsecase

    protected fun testDataDirectory(): Path {
        return FileSystems.getDefault().getPath("data/test/ut", this::class.java.simpleName)
    }

    @Test
    fun test() {
        usecase.import("2018-04-01".toDate(), testDataDirectory())
    }

    private fun String.toDate(): LocalDate {
        return LocalDate.parse(this)
    }
}
