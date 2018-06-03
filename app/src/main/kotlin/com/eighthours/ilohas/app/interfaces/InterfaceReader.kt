package com.eighthours.ilohas.app.interfaces

import com.eighthours.ilohas.framework.reader.ValidationResults
import java.nio.file.Path


interface InterfaceReader<T> {

    fun read(file: Path, block: (Sequence<Pair<T, ValidationResults>>) -> Unit)
}
