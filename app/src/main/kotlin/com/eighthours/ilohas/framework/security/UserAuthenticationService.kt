package com.eighthours.ilohas.framework.security

import com.eighthours.ilohas.domain.system.User
import com.eighthours.ilohas.domain.system.UserRepository
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service
import javax.inject.Inject


@Service
class UserAuthenticationService {

    @Inject
    private lateinit var repository: UserRepository

    @Throws(AuthenticationException::class)
    fun authenticate(username: String, password: String): User {
        val maybeUser = repository.findByName(username)
        val user = maybeUser ?: throw UserNotFoundException(username)

        // TODO authentication

        return user
    }
}


class UserNotFoundException(username: String) : AuthenticationException(username)
