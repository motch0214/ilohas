package com.eighthours.ilohas.domain.system


interface SystemLogRepository {

    @Suppress("FINAL_UPPER_BOUND")
    fun <T : SystemLog> saveAll(logs: Iterable<T>): List<T>
}
