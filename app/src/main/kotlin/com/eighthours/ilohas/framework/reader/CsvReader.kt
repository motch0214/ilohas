package com.eighthours.ilohas.framework.reader

import com.eighthours.ilohas.framework.validation.ValidationResults
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import java.nio.file.Files
import java.nio.file.Path


abstract class CsvReader<T : CsvObject> {

    protected abstract val columns: CsvColumns<T>

    protected abstract val format: CSVFormat

    protected abstract fun createObj(): T

    fun read(file: Path, block: (Sequence<Pair<T, ValidationResults>>) -> Unit) {
        return Files.newBufferedReader(file).use {
            val seq = CSVParser(it, format).asSequence().map(::convert)
            block(seq)
        }
    }

    private fun convert(record: CSVRecord): Pair<T, ValidationResults> {
        val results = ValidationResults()
        val obj = createObj()
        obj.lineNumber = record.recordNumber
        for (col in columns.columnList) {
            val violation = col.set(obj, record[col.header])
            violation?.let { results.add(it) }
        }
        return obj to results
    }
}
