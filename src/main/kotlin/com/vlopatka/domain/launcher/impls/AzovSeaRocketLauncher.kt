package com.vlopatka.domain.launcher.impls

import com.vlopatka.domain.launcher.RocketLauncher
import com.vlopatka.domain.rocket.Rocket
import com.vlopatka.engine.annotation.Singleton

@Singleton
class AzovSeaRocketLauncher : RocketLauncher {
    override fun launch(rocket: Rocket): Int {
        TODO("Not yet implemented")
    }
}