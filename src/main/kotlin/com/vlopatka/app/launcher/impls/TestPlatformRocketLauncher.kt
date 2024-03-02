package com.vlopatka.app.launcher.impls

import com.vlopatka.app.launcher.RocketLauncher
import com.vlopatka.app.speaker.Loudspeaker
import com.vlopatka.app.rocket.Rocket
import com.vlopatka.container.annotation.AutoInject
import com.vlopatka.container.annotation.Singleton
import kotlin.random.Random

@Singleton
class TestPlatformRocketLauncher : RocketLauncher {
    @AutoInject
    private lateinit var loudspeaker: Loudspeaker

    override fun launch(rocket: Rocket): Int {
        loudspeaker.notify("$rocket Land off!")

        val successfulness = Random.nextInt(1, 100)
        return successfulness
    }
}
