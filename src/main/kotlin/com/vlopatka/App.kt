package com.vlopatka

import com.vlopatka.domain.Rocket
import com.vlopatka.engine.context.Application
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService
import com.vlopatka.service.center.NationalSpaceCenter

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