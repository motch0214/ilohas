package com.eighthours.ilohas.web

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class LoginController {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    @RequestMapping("\${web.api-path}/login")
    fun login() {
        log.info("login")
    }
}
