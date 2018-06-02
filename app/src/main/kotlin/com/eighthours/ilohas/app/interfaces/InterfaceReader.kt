package com.eighthours.ilohas.app.interfaces

import java.nio.file.Path


interface InterfaceReader<T> {

    fun read(file: Path, block: (Sequence<T>) -> Unit)
}
