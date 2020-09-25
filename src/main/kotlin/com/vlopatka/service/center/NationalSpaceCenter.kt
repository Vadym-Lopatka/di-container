package com.vlopatka.service.center

import com.vlopatka.annotation.Injection
import com.vlopatka.domain.Rocket
import com.vlopatka.service.launcher.RocketLauncher
import com.vlopatka.service.notifier.Notifier
import com.vlopatka.service.security.SecurityService

class NationalSpaceCenter {

    @Injection
    private lateinit var notifier: Notifier

    @Injection
    private lateinit var securityService: SecurityService

    @Injection
    private lateinit var launcher: RocketLauncher

    fun manageLaunch(rocket: Rocket) {
        notifier.notify("Attention, we are performing a launch.")
        securityService.safetyCheck()

        notifier.periodicNotifier(msg = "Start in:", pendingInMs = 200)

        val launchResult = launcher.launch(rocket)
        notifier.notify("The launch result is: $launchResult - ${launchResult.toReadableMessage()}")
    }
}

private fun Int.toReadableMessage() = when (this) {
    in 1..25 -> "Failure"
    in 26..50 -> "Mainly failure"
    in 51..75 -> "Mainly successful"
    in 75..100 -> "Successful"
    else -> throw IllegalStateException("Can't recognize \"$this\" as launch result.")
}
