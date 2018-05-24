package com.eighthours.ilohas

import com.eighthours.ilohas.batch.BatchTask
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application


private val options = Options().apply {
    addOption("b", "batch", false, "run with batch-mode")
    addOption("p", "profile", true, "spring profile name")
    addOption("t", "task", true, "task name to execute")
}

fun main(vararg args: String) {
    val cmd = DefaultParser().parse(options, args, true)
    val app = SpringApplication(Application::class.java)

    if (cmd.hasOption("b")) {
        app.webApplicationType = WebApplicationType.NONE
    }

    if (cmd.hasOption("p")) {
        app.setDefaultProperties(mapOf("spring.profiles.active" to cmd.getOptionValue("p")))
    }

    val context = app.run(*cmd.args)

    if (cmd.hasOption("t")) {
        val taskName = cmd.getOptionValue("t")
        val task = context.getBean(taskName) as? BatchTask
                ?: throw IllegalArgumentException("$taskName not implemented BatchTask")
        task.execute(cmd.args)
    }
}
