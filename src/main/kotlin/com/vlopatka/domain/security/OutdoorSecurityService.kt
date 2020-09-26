package com.vlopatka.domain.security

import com.vlopatka.domain.notifier.Notifier
import com.vlopatka.engine.annotation.Injection

class OutdoorSecurityService : SecurityService {

    @Injection
    private lateinit var notifier: Notifier

    override fun safetyCheck() {
        println("The area around spaceport is safe")
    }
}