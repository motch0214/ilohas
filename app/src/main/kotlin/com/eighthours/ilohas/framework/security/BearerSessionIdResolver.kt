package com.eighthours.ilohas.framework.security

import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.session.WebSessionIdResolver


private const val HEADER = "Authorization"

private const val SCHEME = "Bearer"


class BearerSessionIdResolver : WebSessionIdResolver {

    override fun resolveSessionIds(exchange: ServerWebExchange): List<String> {
        val headers = exchange.request.headers
        val auth = headers[HEADER] ?: return emptyList()

        return auth.find { it.startsWith(SCHEME) }?.let {
            listOf(it.substring(SCHEME.length + 1)) // `1` removes a white-space
        } ?: emptyList()
    }

    override fun setSessionId(exchange: ServerWebExchange, sessionId: String) {
        // Do nothing because `sessionId` to be sent in response body
    }

    override fun expireSession(exchange: ServerWebExchange) {
        // Do nothing because response has no `sessionId`
    }
}
