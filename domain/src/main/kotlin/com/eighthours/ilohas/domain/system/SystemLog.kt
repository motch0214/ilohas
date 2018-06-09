package com.eighthours.ilohas.domain.system

import java.time.ZonedDateTime
import javax.persistence.*


@Entity
class SystemLog(
        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val level: SystemLogLevel,

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val category: SystemLogCategory,

        @Embedded
        val message: LogMessage,

        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "systemLogId")
        val details: List<SystemLogDetails>,

        val datetime: ZonedDateTime
) {
    @Id
    @GeneratedValue
    val id: Long? = null
}


enum class SystemLogLevel {
    ERROR, WARN, INFO
}


enum class SystemLogCategory {
    INTERFACE,
}
