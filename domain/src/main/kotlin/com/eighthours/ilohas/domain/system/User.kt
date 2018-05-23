package com.eighthours.ilohas.domain.system

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


typealias UserId = Long

@Entity
class User(
        @Column(nullable = false, unique = true)
        val name: String) {

    @Id
    @GeneratedValue
    val id: UserId? = null
}
