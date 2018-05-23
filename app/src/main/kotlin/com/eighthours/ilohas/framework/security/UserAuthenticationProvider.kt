package com.eighthours.ilohas.framework.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException


class UserAuthenticationProvider(
        private val service: UserAuthenticationService) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val principal = authentication.principal
        val credentials = authentication.credentials
        val user = service.authenticate(principal.toString(), credentials.toString())
        return UserToken(user)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return true
    }
}
