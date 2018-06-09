package com.eighthours.ilohas.adapter.jpa.system

import com.eighthours.ilohas.domain.system.SystemLog
import com.eighthours.ilohas.domain.system.SystemLogCategory
import com.eighthours.ilohas.domain.system.SystemLogLevel
import com.eighthours.ilohas.domain.system.SystemLogRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface SystemLogJpaRepository : SystemLogRepository, JpaRepository<SystemLog, Long> {

    fun findByLevelInAndCategoryInAndDatetimeBetween(
            levels: Collection<SystemLogLevel>, categories: Collection<SystemLogCategory>,
            from: ZonedDateTime, to: ZonedDateTime, pageable: Pageable): Page<SystemLog>
}
