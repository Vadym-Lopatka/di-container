package com.vlopatka.app.communication

import com.vlopatka.container.annotation.ConfigBased
import com.vlopatka.container.annotation.Singleton

@Singleton
class Megaphone : CommunicationSystem {

    @ConfigBased
    private lateinit var greeting: String

    override fun notify(msg: String) = println("$greeting $msg")


    override fun notifyPeriodically(msg: String, times: Int, pendingMs: Long) {
        for (i in times downTo 1) {
            notify("$msg $i")
            Thread.sleep(pendingMs)
        }
    }
}