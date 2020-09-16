package com.vlopatka.service.spaceRocketCenter

import com.vlopatka.domain.Rocket
import com.vlopatka.service.notifier.ConsoleNotifier
import com.vlopatka.service.notifier.Notifier
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService
import kotlin.random.Random


class SpaceRocketCenter {
    private val notifier: Notifier = ConsoleNotifier()
    private val securityService: SecurityService = OutdoorSecurityService()

    fun manageLaunch(rocket: Rocket): Unit {
        notifier.notify("Attention, we are performing a launch.")
        securityService.safetyCheck()

        notifier.periodicNotifier(msg = "Start in:", counter = 10, pending = 250)
        val result = launch(rocket)

        notifier.notify("The launch result is: ${result.toReadableMessage()}")
    }

    private fun launch(rocket: Rocket): Boolean {
        println("Land off!")
        return Random.nextInt(0, 100) > 50
    }
}

private fun Boolean.toReadableMessage() = if (this) "Success" else "Failure"
