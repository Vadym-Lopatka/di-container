package com.vlopatka.app.launcher.impls

import com.vlopatka.app.launcher.RocketLauncher
import com.vlopatka.app.notifier.Notifier
import com.vlopatka.app.rocket.Rocket
import com.vlopatka.dicontainer.annotation.Injection
import com.vlopatka.dicontainer.annotation.Singleton
import kotlin.random.Random

@Singleton
class TestPlatformRocketLauncher : RocketLauncher {
    @Injection
    private lateinit var notifier: Notifier

    override fun launch(rocket: Rocket): Int {
        notifier.notify("$rocket Land off!")

        val successfulness = Random.nextInt(1, 100)
        return successfulness
    }
}
