package com.eighthours.ilohas.framework.reader

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import java.nio.file.Files
import java.nio.file.Path


abstract class CsvReader<out T> {

    protected abstract val format: CSVFormat

    fun read(file: Path, block: (Sequence<T>) -> Unit) {
        return Files.newBufferedReader(file).use {
            val seq = CSVParser(it, format).asSequence().map { it.parse() }
            block(seq)
        }
    }

    protected abstract fun CSVRecord.parse(): T
}
