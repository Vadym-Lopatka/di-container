package com.vlopatka.domain.service.launcher

import com.vlopatka.domain.service.rocket.Rocket

interface RocketLauncher {
    fun launch(rocket: Rocket): Int
}