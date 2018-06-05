package com.eighthours.ilohas.domain.system

import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class LogMessage(
        @Column(nullable = false)
        val messageId: String,

        val arg1: String? = null,

        val arg2: String? = null,

        val arg3: String? = null,

        val arg4: String? = null,

        val arg5: String? = null
)
