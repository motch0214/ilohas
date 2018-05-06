package com.eighthours.ilohas.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


@Configuration
@EnableJpaRepositories("com.eighthours.ilohas.adapter.jpa")
@EnableTransactionManagement
class PersistenceConfig {

    @Bean
    fun entityManagerFactory(): EntityManagerFactory {
        return Persistence.createEntityManagerFactory("ilohas")
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            entityManagerFactory = emf
        }
    }
}
