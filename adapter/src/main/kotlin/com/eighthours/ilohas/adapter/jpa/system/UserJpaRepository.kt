package com.eighthours.ilohas.adapter.jpa.system

import com.eighthours.ilohas.domain.system.User
import com.eighthours.ilohas.domain.system.UserId
import com.eighthours.ilohas.domain.system.UserRepository
import org.springframework.data.jpa.repository.JpaRepository


interface UserJpaRepository : UserRepository, JpaRepository<User, UserId>
