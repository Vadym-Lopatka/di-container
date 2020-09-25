package com.vlopatka.service.launcher

import com.vlopatka.engine.annotation.Injection
import com.vlopatka.engine.annotation.Singleton
import com.vlopatka.domain.Rocket
import com.vlopatka.service.notifier.Notifier
import kotlin.random.Random

@Singleton
class TestPlatformRocketLauncher : RocketLauncher {
    @Injection
    private lateinit var notifier: Notifier

    override fun launch(rocket: Rocket) :Int {
        notifier.notify("Land off!")

        val successfulness = Random.nextInt(1, 100)
        return successfulness
    }
}
