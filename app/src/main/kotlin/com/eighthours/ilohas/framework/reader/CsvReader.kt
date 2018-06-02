package com.eighthours.ilohas.framework.reader

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import java.nio.file.Files
import java.nio.file.Path


abstract class CsvReader<T> {

    protected abstract val columns: CsvColumns<T>

    protected abstract val format: CSVFormat

    protected abstract fun createObj(): T

    fun read(file: Path, block: (Sequence<T>) -> Unit) {
        return Files.newBufferedReader(file).use {
            val seq = CSVParser(it, format).asSequence().map(::convert)
            block(seq)
        }
    }

    private fun convert(record: CSVRecord): T {
        val obj = createObj()
        for (col in columns.columns) {
            col.property?.set(obj, record[col.header])
        }
        return obj
    }
}
