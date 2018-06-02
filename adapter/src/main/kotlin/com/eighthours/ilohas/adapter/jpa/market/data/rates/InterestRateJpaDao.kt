package com.eighthours.ilohas.adapter.jpa.market.data.rates

import com.eighthours.ilohas.app.interfaces.market.data.rates.InterestRateDao
import com.eighthours.ilohas.domain.market.data.rates.InterestRate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.inject.Inject


@Component
class InterestRateJpaDao : InterestRateDao {

    @Inject
    private lateinit var repository: InterestRateJpaRepository

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun saveAll(rates: Collection<InterestRate>) {
        repository.saveAll(rates)
    }
}


interface InterestRateJpaRepository : JpaRepository<InterestRate, Long>
