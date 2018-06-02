package com.eighthours.ilohas.domain.system


interface UserRepository {

    @Suppress("FINAL_UPPER_BOUND")
    fun <T : User> saveAll(users: Iterable<T>): List<T>

    fun findByName(name: String): User?
}
