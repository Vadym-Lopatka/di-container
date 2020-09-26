package com.vlopatka.domain.security

import com.vlopatka.domain.notifier.Notifier
import com.vlopatka.engine.annotation.Injection
import com.vlopatka.engine.annotation.Singleton

@Singleton
class OutdoorSecurityService : SecurityService {

    @Injection
    private lateinit var notifier: Notifier

    override fun safetyCheck() {
        notifier.notify("The area around spaceport is safe")
    }
}