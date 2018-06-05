package com.eighthours.ilohas.domain.system

import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class SystemLogDetails(
        @Embedded
        val message: LogMessage
) {
    @Id
    @GeneratedValue
    val id: Long? = null
}
