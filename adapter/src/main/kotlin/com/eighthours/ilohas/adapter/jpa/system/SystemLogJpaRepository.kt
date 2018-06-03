package com.eighthours.ilohas.adapter.jpa.system

import com.eighthours.ilohas.domain.system.SystemLog
import com.eighthours.ilohas.domain.system.SystemLogRepository
import org.springframework.data.jpa.repository.JpaRepository


interface SystemLogJpaRepository : SystemLogRepository, JpaRepository<SystemLog, Long>
