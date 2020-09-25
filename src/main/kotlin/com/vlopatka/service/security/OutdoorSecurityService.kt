package com.vlopatka.service.security

import com.vlopatka.annotation.Injection
import com.vlopatka.service.notifier.Notifier

class OutdoorSecurityService : SecurityService {

    @Injection
    private lateinit var notifier: Notifier

    override fun safetyCheck() {
        println("The area around spaceport is safe")
    }
}