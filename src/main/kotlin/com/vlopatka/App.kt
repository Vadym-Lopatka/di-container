package com.vlopatka

import com.vlopatka.domain.Rocket
import com.vlopatka.engine.ObjectFactory
import com.vlopatka.service.spaceRocketCenter.SpaceRocketCenter

fun main(args: Array<String>) {
    val rocketCenter = ObjectFactory.createObject(SpaceRocketCenter::class.java)
    rocketCenter.manageLaunch(Rocket())
}