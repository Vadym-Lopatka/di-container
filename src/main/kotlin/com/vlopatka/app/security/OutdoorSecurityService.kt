package com.vlopatka.app.security

import com.vlopatka.app.notifier.Notifier
import com.vlopatka.dicontainer.annotation.Injection
import com.vlopatka.dicontainer.annotation.Singleton

@Singleton
class OutdoorSecurityService : SecurityService {

    @Injection
    private lateinit var notifier: Notifier

    override fun safetyCheck() {
        notifier.notify("The area around spaceport is safe")
    }
}