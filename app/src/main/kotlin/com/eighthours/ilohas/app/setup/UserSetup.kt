package com.eighthours.ilohas.app.setup

import com.eighthours.ilohas.domain.system.User
import com.eighthours.ilohas.domain.system.UserRepository
import com.eighthours.ilohas.framework.reader.CsvReader
import org.apache.commons.csv.CSVFormat
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
        UserReader().read(file) { users ->
            repository.saveAll(users.toList())
        }
    }
}


private class UserReader : CsvReader<User>() {

    override val format: CSVFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader()

    override fun CSVRecord.parse() = User(name = this["Name"])
}
