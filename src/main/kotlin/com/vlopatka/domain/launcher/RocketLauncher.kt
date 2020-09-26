package com.vlopatka.domain.launcher

import com.vlopatka.domain.rocket.Rocket

interface RocketLauncher {
    fun launch(rocket: Rocket): Int
}