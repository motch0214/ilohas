package com.eighthours.ilohas.batch

import com.eighthours.ilohas.app.setup.InitialDataSetupService
import org.springframework.stereotype.Component
import java.nio.file.Paths
import javax.inject.Inject


@Component("InitialDataSetupTask")
class InitialDataSetupTask : BatchTask {

    @Inject
    private lateinit var service: InitialDataSetupService

    override fun execute(args: Array<String>) {
        val directories = args.map { Paths.get(it) }
        service.setup(directories)
    }
}
