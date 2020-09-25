package com.vlopatka

import com.vlopatka.domain.Rocket
import com.vlopatka.engine.application.Application
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService
import com.vlopatka.service.spaceRocketCenter.SpaceRocketCenter

fun main(args: Array<String>) {
//    val rocketCenter = ObjectFactory.createObject(SpaceRocketCenter::class.java)
    val context = Application.run(
        packageToScan = "com.vlopatka",
        interfaceToImplementationMap = mapOf(
            SecurityService::class.java to OutdoorSecurityService::class.java
        )
    )

    val spaceRocketCenter = context.getObject(SpaceRocketCenter::class.java)
    spaceRocketCenter.manageLaunch(Rocket())
}