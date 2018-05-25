package com.eighthours.ilohas.controller

import com.eighthours.ilohas.framework.security.UserToken
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@RestController
class LoginController {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    @PostMapping("\${web.api-path}/login")
    fun login(exchange: ServerWebExchange): Mono<UserTokenMessage> {
        val token = SecurityContextHolder.getContext().authentication as? UserToken
                ?: throw IllegalStateException("Invalid authentication")

        return exchange.session.map {
            UserTokenMessage(it.id, token.user.name)
        }
    }
}

data class UserTokenMessage(
        val sessionId: String,
        val displayName: String
)
