package com.vlopatka.app.security

import com.vlopatka.app.notifier.Notifier
import com.vlopatka.container.annotation.AutoInject
import com.vlopatka.container.annotation.Singleton

@Singleton
class OutdoorSecurityService : SecurityService {

    @AutoInject
    private lateinit var notifier: Notifier

    override fun safetyCheck() {
        notifier.notify("The area around spaceport is safe")
    }
}