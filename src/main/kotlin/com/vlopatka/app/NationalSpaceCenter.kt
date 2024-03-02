package com.vlopatka.app

import com.vlopatka.app.communication.CommunicationSystem
import com.vlopatka.app.launcher.RocketLauncher
import com.vlopatka.app.rocket.Rocket
import com.vlopatka.app.security.SecurityPerson
import com.vlopatka.container.annotation.Injection
import com.vlopatka.container.annotation.Singleton

@Singleton
class NationalSpaceCenter {

    @Injection
    private lateinit var communicationSystem: CommunicationSystem

    @Injection
    private lateinit var securityPerson: SecurityPerson

    @Injection
    private lateinit var launcher: RocketLauncher

    fun manageLaunch(rocket: Rocket) {
        communicationSystem.notify("$rocket is ready to start")

        securityPerson.safetyCheck()

        communicationSystem.notifyPeriodically(msg = "$rocket Start in:")

        val launchResult = launcher.launch(rocket)
        communicationSystem.notify("$rocket launch result is: $launchResult - ${launchResult.toReadableMessage()}")
    }
}

private fun Int.toReadableMessage() = when (this) {
    in 1..25 -> "Failure"
    in 26..50 -> "Mainly failure"
    in 51..75 -> "Mainly successful"
    in 75..100 -> "Successful"
    else -> throw IllegalStateException("Can't recognize \"$this\" as launch result.")
}
