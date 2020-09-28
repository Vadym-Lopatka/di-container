package com.vlopatka.domain

import com.vlopatka.domain.launcher.RocketLauncher
import com.vlopatka.domain.notifier.Notifier
import com.vlopatka.domain.rocket.Rocket
import com.vlopatka.domain.security.SecurityService
import com.vlopatka.engine.annotation.Injection
import com.vlopatka.engine.annotation.Singleton

@Singleton
class NationalSpaceCenter {

    @Injection
    private lateinit var notifier: Notifier

    @Injection
    private lateinit var securityService: SecurityService

    @Injection
    private lateinit var launcher: RocketLauncher

    fun manageLaunch(rocket: Rocket) {
        notifier.notify("$rocket is ready to start")

        securityService.safetyCheck()

        notifier.periodicNotifier(msg = "$rocket Start in:")

        val launchResult = launcher.launch(rocket)
        notifier.notify("$rocket launch result is: $launchResult - ${launchResult.toReadableMessage()}")
    }
}

private fun Int.toReadableMessage() = when (this) {
    in 1..25 -> "Failure"
    in 26..50 -> "Mainly failure"
    in 51..75 -> "Mainly successful"
    in 75..100 -> "Successful"
    else -> throw IllegalStateException("Can't recognize \"$this\" as launch result.")
}
