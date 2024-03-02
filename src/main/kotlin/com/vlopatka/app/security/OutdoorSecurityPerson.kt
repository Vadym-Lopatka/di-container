package com.vlopatka.app.security

import com.vlopatka.app.communication.CommunicationSystem
import com.vlopatka.container.annotation.Injection
import com.vlopatka.container.annotation.Singleton

@Singleton
class OutdoorSecurityPerson : SecurityPerson {

    @Injection
    private lateinit var communicationSystem: CommunicationSystem

    override fun safetyCheck() {
        communicationSystem.notify("The area around spaceport is safe")
    }
}