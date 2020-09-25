package com.vlopatka.service.launcher

import com.vlopatka.domain.Rocket

interface RocketLauncher {
    fun launch(rocket: Rocket): Int
}