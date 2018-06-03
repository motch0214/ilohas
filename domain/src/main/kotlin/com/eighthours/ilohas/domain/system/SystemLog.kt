package com.eighthours.ilohas.domain.system

import javax.persistence.*


@Entity
class SystemLog(
        @Column(nullable = false)
        val messageId: String,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable
        @OrderColumn
        val arguments: List<String> = emptyList(),

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val type: SystemLogType
) {
    @Id
    @GeneratedValue
    val id: Long? = null

    companion object {

        fun error(messageId: String, vararg arguments: String) = SystemLog(messageId, arguments.toList(), SystemLogType.ERROR)

        fun warn(messageId: String, vararg arguments: String) = SystemLog(messageId, arguments.toList(), SystemLogType.WARN)

        fun info(messageId: String, vararg arguments: String) = SystemLog(messageId, arguments.toList(), SystemLogType.INFO)
    }
}


enum class SystemLogType {
    ERROR, WARN, INFO
}
