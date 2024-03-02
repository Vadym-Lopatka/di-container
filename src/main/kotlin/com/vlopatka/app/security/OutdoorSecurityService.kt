package com.vlopatka.app.security

import com.vlopatka.app.speaker.Loudspeaker
import com.vlopatka.container.annotation.AutoInject
import com.vlopatka.container.annotation.Singleton

@Singleton
class OutdoorSecurityService : SecurityService {

    @AutoInject
    private lateinit var loudspeaker: Loudspeaker

    override fun safetyCheck() {
        loudspeaker.notify("The area around spaceport is safe")
    }
}