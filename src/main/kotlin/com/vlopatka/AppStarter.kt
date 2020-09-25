package com.vlopatka

import com.vlopatka.domain.service.rocket.Rocket
import com.vlopatka.domain.service.center.NationalSpaceCenter
import com.vlopatka.domain.service.security.OutdoorSecurityService
import com.vlopatka.domain.service.security.SecurityService
import com.vlopatka.engine.context.Application

fun main(args: Array<String>) {
    val context = Application.run(
        packageToScan = "com.vlopatka",
        interfaceToImplementationMap = defineImplementations()
    )

    val spaceRocketCenter = context.getObject(NationalSpaceCenter::class.java)
    spaceRocketCenter.manageLaunch(Rocket())
}


/**
 * Define here which particular implementation to use when your interface has multiple implementations
 * @see com.vlopatka.service.security.SecurityService
 *
 * p.s. In prod-like products, it would be more convenient to read this map from an outer source.
 * For example,  a config file.
 */
private fun defineImplementations(): Map<Class<*>, Class<*>> = mapOf(
    SecurityService::class.java to OutdoorSecurityService::class.java
)