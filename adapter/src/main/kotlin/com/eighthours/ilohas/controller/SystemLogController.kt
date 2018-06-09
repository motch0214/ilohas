package com.eighthours.ilohas.controller

import com.eighthours.ilohas.adapter.jpa.system.SystemLogJpaDao
import com.eighthours.ilohas.domain.system.SystemLog
import com.eighthours.ilohas.domain.system.SystemLogCategory
import com.eighthours.ilohas.domain.system.SystemLogLevel
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.time.ZonedDateTime
import javax.inject.Inject


@RestController
class SystemLogController {

    @Inject
    private lateinit var dao: SystemLogJpaDao

    @PostMapping("\${web.api-path}/system-log")
    fun querySystemLog(@RequestBody message: SystemLogQueryMessage): Flux<SystemLogMessage> {
        val page = dao.query(message.level, message.category, message.fromDatetime, message.toDatetime,
                PageRequest.of(message.page, message.pageSize, message.order.toSort()))
        return page.toFlux().map { SystemLogMessage.from(it) }
    }
}


data class SystemLogQueryMessage(
        val level: SystemLogLevel? = null,
        val category: SystemLogCategory? = null,
        val fromDatetime: ZonedDateTime? = null,
        val toDatetime: ZonedDateTime? = null,
        val page: Int,
        val pageSize: Int,
        val order: List<QueryOrder> = emptyList()
)


data class SystemLogMessage(
        val id: Long,
        val level: SystemLogLevel,
        val category: SystemLogCategory,
        val message: String,
        val datetime: ZonedDateTime
) {
    companion object {
        fun from(entity: SystemLog) = SystemLogMessage(
                id = entity.id!!,
                level = entity.level,
                category = entity.category,
                message = entity.message.messageId,
                datetime = entity.datetime)
    }
}
