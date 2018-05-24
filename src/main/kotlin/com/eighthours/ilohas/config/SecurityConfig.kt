package com.eighthours.ilohas.config

import com.eighthours.ilohas.framework.security.UserAuthenticationProvider
import com.eighthours.ilohas.framework.security.UserAuthenticationService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import javax.inject.Inject


@Configuration
class SecurityConfig {

    @Value("\${web.resources-path}")
    private lateinit var resourcesPath: String

    @Value("\${web.api-path}")
    private lateinit var apiPath: String

    // `ServerHttpSecurity` will be null when batch-mode
    @Inject
    private var http: ServerHttpSecurity? = null

    @Bean
    fun springSecurityFilterChain(manager: ReactiveAuthenticationManager): SecurityWebFilterChain? {
        val http = http ?: return null
        http.authorizeExchange()
                .pathMatchers("$resourcesPath/**").permitAll()
                .anyExchange().authenticated()

        http.formLogin()
                .loginPage("$resourcesPath/index.html#/login")
                .requiresAuthenticationMatcher(
                        ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "$apiPath/login"))

        http.authenticationManager(manager)

        http.csrf().disable()

        return http.build()
    }

    @Bean
    fun authenticationManager(authenticationService: UserAuthenticationService): ReactiveAuthenticationManager {
        val providers = listOf(UserAuthenticationProvider(authenticationService))
        return ReactiveAuthenticationManagerAdapter(ProviderManager(providers))
    }
}
