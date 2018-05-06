package com.eighthours.ilohas.config

import org.springframework.context.annotation.*


@Configuration
@Import(PersistenceConfig::class)
@ComponentScan("com.eighthours.ilohas.app", "com.eighthours.ilohas.adapter")
class AppConfig
