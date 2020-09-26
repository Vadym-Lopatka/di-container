package com.vlopatka.domain.launcher.impls

import com.vlopatka.domain.launcher.RocketLauncher
import com.vlopatka.domain.notifier.Notifier
import com.vlopatka.domain.rocket.Rocket
import com.vlopatka.engine.annotation.Injection
import com.vlopatka.engine.annotation.Singleton
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
