package com.vlopatka.service.spaceRocketCenter

import com.vlopatka.annotation.Injection
import com.vlopatka.domain.Rocket
import com.vlopatka.engine.ObjectFactory
import com.vlopatka.service.notifier.Notifier
import com.vlopatka.service.security.SecurityService
import kotlin.random.Random

class SpaceRocketCenter {
    @Injection
    private lateinit var notifier: Notifier

    @Injection
    private lateinit var securityService: SecurityService

    fun manageLaunch(rocket: Rocket) {
        notifier.notify("Attention, we are performing a launch.")
        securityService.safetyCheck()

        notifier.periodicNotifier(msg = "Start in:", counter = 10, pendingInMs = 250)
        val result = doLaunch(rocket)

        notifier.notify("The launch result is: ${result.toReadableMessage()}")
    }

    private fun doLaunch(rocket: Rocket): Boolean {
        println("Land off!")
        val launchSuccessfulness = Random.nextInt(0, 100)
        return isSuccessFul(launchSuccessfulness)
    }

    private fun isSuccessFul(launchSuccessfulness: Int): Boolean {
        return launchSuccessfulness > 50
    }
}

private fun Boolean.toReadableMessage() = if (this) "Success" else "Failure"
