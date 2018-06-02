package com.eighthours.ilohas.domain.system


interface UserRepository {

    fun <T : User> saveAll(users: Iterable<T>): List<T>

    fun findByName(name: String): User?
}
