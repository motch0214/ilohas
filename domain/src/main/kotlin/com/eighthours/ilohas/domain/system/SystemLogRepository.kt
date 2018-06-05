package com.eighthours.ilohas.domain.system


interface SystemLogRepository {

    fun save(log: SystemLog): SystemLog

    @Suppress("FINAL_UPPER_BOUND")
    fun <T : SystemLog> saveAll(logs: Iterable<T>): List<T>
}
