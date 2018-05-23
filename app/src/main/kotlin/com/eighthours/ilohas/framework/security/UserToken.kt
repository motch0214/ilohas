package com.eighthours.ilohas.framework.security

import com.eighthours.ilohas.domain.system.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority


class UserToken(val user: User) : Authentication {

    private var trusted = true

    override fun getName(): String {
        return user.name
    }

    override fun getPrincipal(): Any {
        return user.name
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO()
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if (isAuthenticated) {
            throw IllegalArgumentException("Cannot set this token to trusted, use constructor.")
        }
        trusted = false
    }

    override fun isAuthenticated(): Boolean {
        return trusted
    }

    override fun getDetails(): Any {
        TODO()
    }
}
