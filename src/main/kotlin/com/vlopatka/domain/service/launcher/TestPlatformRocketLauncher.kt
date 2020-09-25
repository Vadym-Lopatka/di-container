package com.vlopatka.domain.service.launcher

import com.vlopatka.domain.service.notifier.Notifier
import com.vlopatka.domain.service.rocket.Rocket
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
