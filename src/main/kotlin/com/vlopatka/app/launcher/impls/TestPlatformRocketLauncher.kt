package com.vlopatka.app.launcher.impls

import com.vlopatka.app.communication.CommunicationSystem
import com.vlopatka.app.launcher.RocketLauncher
import com.vlopatka.app.rocket.Rocket
import com.vlopatka.container.annotation.AutoInject
import com.vlopatka.container.annotation.Singleton
import kotlin.random.Random

@Singleton
class TestPlatformRocketLauncher : RocketLauncher {
    @AutoInject
    private lateinit var communicationSystem: CommunicationSystem

    override fun launch(rocket: Rocket): Int {
        communicationSystem.notify("$rocket Land off!")

        val successfulness = Random.nextInt(1, 100)
        return successfulness
    }
}
