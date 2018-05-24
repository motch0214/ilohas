package com.eighthours.ilohas

import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application


private val options = Options().apply {
    addOption("b", "batch", false, "run with batch-mode")
}

fun main(vararg args: String) {
    val cmd = DefaultParser().parse(options, args, true)
    val app = SpringApplication(Application::class.java)

    if (cmd.hasOption("b")) {
        app.webApplicationType = WebApplicationType.NONE
    }

    app.run(*args)
}
