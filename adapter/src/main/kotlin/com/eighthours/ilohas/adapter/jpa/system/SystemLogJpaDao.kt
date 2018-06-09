package com.eighthours.ilohas.adapter.jpa.system

import com.eighthours.ilohas.domain.system.SystemLog
import com.eighthours.ilohas.domain.system.SystemLogCategory
import com.eighthours.ilohas.domain.system.SystemLogLevel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import javax.inject.Inject


private val MIN_DATETIME = ZonedDateTime.parse("1900-01-01T00:00:00+00:00[GMT]")

private val MAX_DATETIME = ZonedDateTime.parse("9999-12-31T23:59:59+00:00[GMT]")

@Component
class SystemLogJpaDao {

    @Inject
    private lateinit var repository: SystemLogJpaRepository

    fun query(level: SystemLogLevel? = null, category: SystemLogCategory? = null,
              fromDatetime: ZonedDateTime? = null, toDatetime: ZonedDateTime? = null,
              pageable: Pageable): Page<SystemLog> {
        // TODO performance
        val levels = level?.let { listOf(it) } ?: SystemLogLevel.values().asList()
        val categories = category?.let { listOf(it) } ?: SystemLogCategory.values().asList()
        val from = fromDatetime ?: MIN_DATETIME
        val to = toDatetime ?: MAX_DATETIME
        return repository.findByLevelInAndCategoryInAndDatetimeBetween(levels, categories, from, to, pageable)
    }
}

