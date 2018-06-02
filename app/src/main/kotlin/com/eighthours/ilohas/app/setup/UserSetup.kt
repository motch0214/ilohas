package com.eighthours.ilohas.app.setup

import com.eighthours.ilohas.domain.system.User
import com.eighthours.ilohas.domain.system.UserRepository
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.nio.file.Path
import javax.inject.Inject


@Component
@Transactional
class UserSetup : DataSetup {

    @Inject
    private lateinit var repository: UserRepository

    override val filename = "user.csv"

    override fun setup(file: Path) {
        readCsv(file) { records ->
            val users = records.map(::convert).toList()
            repository.saveAll(users.toList())
        }
    }

    private fun convert(record: CSVRecord) = User(
            name = record["Name"]
    )
}
