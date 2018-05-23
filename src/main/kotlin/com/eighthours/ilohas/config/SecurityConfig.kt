package com.eighthours.ilohas.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
class SecurityConfig {

    @Value("\${web.resources-path}")
    private lateinit var resourcesPath: String

    @Value("\${web.api-path}")
    private lateinit var apiPath: String

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.authorizeExchange()
                .pathMatchers("$resourcesPath/**", "$apiPath/login").permitAll()
                .anyExchange().authenticated()

        http.formLogin()
                .loginPage("$resourcesPath/index.html#/login")

        http.csrf().disable()

        return http.build()
    }
}
