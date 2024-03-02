package com.vlopatka.app.speaker

import com.vlopatka.container.annotation.ConfigValue
import com.vlopatka.container.annotation.Singleton

@Singleton
class Megaphone : Loudspeaker {

    @ConfigValue
    private lateinit var greeting: String

    override fun notify(msg: String) {
        println("$greeting $msg")
    }

    override fun periodicNotifier(msg: String, counter: Int, pendingInMs: Long) {
        for (i in counter downTo 1) {
            notify("$msg $i")
            Thread.sleep(pendingInMs)
        }
    }
}