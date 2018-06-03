package com.eighthours.ilohas.framework.reader


class Which<L : Any, R : Any> private constructor(val left: L?, val right: R?) {

    companion object {

        fun <L : Any, R : Any> left(l: L) = Which<L, R>(l, null)

        fun <L : Any, R : Any> right(r: R) = Which<L, R>(null, r)
    }

    operator fun component1(): L? = left

    operator fun component2(): R? = right
}
