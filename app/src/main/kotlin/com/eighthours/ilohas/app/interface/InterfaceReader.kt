package com.eighthours.ilohas.app.`interface`;

import java.nio.file.Path


interface InterfaceReader<T> {

    fun read(file: Path, block: (Sequence<T>) -> Unit)
}
