package com.eighthours.ilohas.framework


class Result<out T, out E : Any>(val result: T?, val error: E?) {

    operator fun component1(): T? = result

    operator fun component2(): E? = error

    inline fun ifError(block: (E) -> Unit): T {
        if (error != null) {
            block(error)
        }
        @Suppress("UNCHECKED_CAST")
        return result as T
    }
}

fun <T, E : Any> ok(result: T) = Result<T, E>(result, null)

fun <T, E : Any> err(error: E) = Result<T, E>(null, error)
