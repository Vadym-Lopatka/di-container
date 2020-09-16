package com.vlopatka

import com.vlopatka.domain.Rocket
import com.vlopatka.service.spaceRocketCenter.SpaceRocketCenter

fun main(args: Array<String>) {
    val rocketCenter = SpaceRocketCenter()
    rocketCenter.manageLaunch(Rocket())
}