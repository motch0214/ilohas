package com.eighthours.ilohas.app.setup

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import java.nio.file.Path
import javax.inject.Inject


// Ordered `DataSetup`
private val setupClasses = listOf<Class<out DataSetup>>(
        UserSetup::class.java
)


@Service
class InitialDataSetupService {

    @Inject
    private lateinit var context: ApplicationContext

    fun setup(directories: List<Path>) {
        for (setupClass in setupClasses) {
            val instance = context.getBean(setupClass)
            for (directory in directories) {
                val file = directory.resolve(instance.filename).toFile()
                if (file.exists() && file.isFile) {
                    instance.setup(file.toPath())
                }
            }
        }
    }
}


interface DataSetup {

    val filename: String

    fun setup(file: Path)
}
