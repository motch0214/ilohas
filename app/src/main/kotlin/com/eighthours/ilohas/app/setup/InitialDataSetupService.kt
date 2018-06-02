package com.eighthours.ilohas.app.setup

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import javax.inject.Inject


// Ordered `DataSetup`
private val setupClasses = listOf(
        UserSetup::class
)


@Service
class InitialDataSetupService {

    @Inject
    private lateinit var context: ApplicationContext

    fun setup(directories: List<Path>) {
        for (setupClass in setupClasses) {
            val instance = context.getBean(setupClass.java)
            for (directory in directories) {
                val file = directory.resolve(instance.filename).toFile()
                if (file.exists() && file.isFile) {
                    instance.setup(file.toPath())
                }
            }
        }
    }
}


private val DEFAULT_CSV_FORMAT = CSVFormat.DEFAULT.withFirstRecordAsHeader()

interface DataSetup {

    val filename: String

    fun setup(file: Path)

    fun readCsv(file: Path, block: (Sequence<CSVRecord>) -> Unit) {
        Files.newBufferedReader(file).use {
            val seq = CSVParser.parse(it, DEFAULT_CSV_FORMAT).asSequence()
            block(seq)
        }
    }
}
