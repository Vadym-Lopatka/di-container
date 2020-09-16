package com.vlopatka.service.spaceRocketCenter

import com.vlopatka.domain.Rocket
import com.vlopatka.reflection.ObjectFactory
import com.vlopatka.service.notifier.Notifier
import com.vlopatka.service.security.SecurityService
import kotlin.random.Random

class SpaceRocketCenter {
    private val notifier: Notifier = ObjectFactory.createObject(Notifier::class.java)
    private val securityService: SecurityService = ObjectFactory.createObject(SecurityService::class.java)

    fun manageLaunch(rocket: Rocket) {
        notifier.notify("Attention, we are performing a launch.")
        securityService.safetyCheck()

        notifier.periodicNotifier(msg = "Start in:", counter = 10, pending = 250)
        val result = launch(rocket)

        notifier.notify("The launch result is: ${result.toReadableMessage()}")
    }

    private fun launch(rocket: Rocket): Boolean {
        println("Land off!")
        val launchSuccessfulness = Random.nextInt(0, 100)
        return isSuccessFul(launchSuccessfulness)
    }

    private fun isSuccessFul(launchSuccessfulness: Int): Boolean {
        return launchSuccessfulness > 50
    }
}

private fun Boolean.toReadableMessage() = if (this) "Success" else "Failure"
