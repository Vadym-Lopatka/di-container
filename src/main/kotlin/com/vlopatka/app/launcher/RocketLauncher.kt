package com.vlopatka.app.launcher

import com.vlopatka.app.rocket.Rocket

interface RocketLauncher {
    fun launch(rocket: Rocket): Int
}