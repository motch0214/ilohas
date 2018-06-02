package com.eighthours.ilohas

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application

fun main(vararg args: String) {
    SpringApplication.run(Application::class.java, *args)
}
