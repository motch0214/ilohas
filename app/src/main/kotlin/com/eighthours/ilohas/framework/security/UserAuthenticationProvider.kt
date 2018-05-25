package com.eighthours.ilohas.framework.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder


class UserAuthenticationProvider(
        private val service: UserAuthenticationService) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val principal = authentication.principal
        val credentials = authentication.credentials

        // Throw `AuthenticationException when rejected
        val user = service.authenticate(principal.toString(), credentials.toString())

        val token = UserToken(user) // Authenticated
        SecurityContextHolder.getContext().authentication = token
        return token
    }

    override fun supports(authentication: Class<*>): Boolean {
        return true
    }
}
