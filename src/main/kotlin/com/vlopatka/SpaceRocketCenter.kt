package com.vlopatka

import kotlin.random.Random


class SpaceRocketCenter {
    private val notifier: Notifier = ConsoleNotifier()
    private val securityService: SecurityService = OutdoorSecurityService()

    fun manageLaunch(rocket: Rocket): Unit {
        notifier.notify("Attention, we are performing a launch.")
        securityService.safetyCheck()

        notifier.periodicNotifier("Start in:", 10)
        val result = launch(rocket)

        notifier.notify("The launch result is: ${result.toReadableMessage()}")
    }

    private fun launch(rocket: Rocket): Boolean {
        println("Land off!")
        return Random.nextInt(0, 100) > 50
    }
}

private fun Boolean.toReadableMessage() = if (this) "Success" else "Failure"
