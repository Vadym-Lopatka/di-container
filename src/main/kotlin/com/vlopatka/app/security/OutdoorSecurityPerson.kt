package com.vlopatka.app.security

import com.vlopatka.app.communication.CommunicationSystem
import com.vlopatka.container.annotation.AutoInject
import com.vlopatka.container.annotation.Singleton

@Singleton
class OutdoorSecurityPerson : SecurityPerson {

    @AutoInject
    private lateinit var communicationSystem: CommunicationSystem

    override fun safetyCheck() {
        communicationSystem.notify("The area around spaceport is safe")
    }
}