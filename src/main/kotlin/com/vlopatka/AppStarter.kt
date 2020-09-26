package com.vlopatka

import com.vlopatka.domain.NationalSpaceCenter
import com.vlopatka.domain.launcher.RocketLauncher
import com.vlopatka.domain.launcher.TestPlatformRocketLauncher
import com.vlopatka.domain.rocket.Rocket
import com.vlopatka.engine.context.Application

fun main(args: Array<String>) {
    val context = Application.run(
        packageToScan = "com.vlopatka",
        interfaceToImplementationMap = configureInterfaceImplementations()
    )

    val spaceCenter = context.getObject(NationalSpaceCenter::class.java)
    spaceCenter.manageLaunch(Rocket())
}


/**
 * Define here which particular implementation to use when your interface has multiple implementations
 * @see com.vlopatka.domain.service.launcher.RocketLauncher
 *
 * p.s. In prod-like products, it would be more convenient to read this map from an outer source.
 * For example,  a config file.
 */
private fun configureInterfaceImplementations(): Map<Class<*>, Class<*>> = mapOf(
    RocketLauncher::class.java to TestPlatformRocketLauncher::class.java
)