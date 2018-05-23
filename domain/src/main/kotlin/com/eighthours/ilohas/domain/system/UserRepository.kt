package com.eighthours.ilohas.domain.system


interface UserRepository {

    fun findByName(name: String): User?
}
