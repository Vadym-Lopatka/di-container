package com.vlopatka.app

import com.vlopatka.app.launcher.RocketLauncher
import com.vlopatka.app.notifier.Notifier
import com.vlopatka.app.rocket.Rocket
import com.vlopatka.app.security.SecurityService
import com.vlopatka.container.annotation.AutoInject
import com.vlopatka.container.annotation.Singleton

@Singleton
class NationalSpaceCenter {

    @AutoInject
    private lateinit var notifier: Notifier

    @AutoInject
    private lateinit var securityService: SecurityService

    @AutoInject
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
