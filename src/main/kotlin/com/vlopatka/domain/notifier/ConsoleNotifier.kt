package com.vlopatka.domain.notifier

import com.vlopatka.engine.annotation.InjectProperty
import com.vlopatka.engine.annotation.Singleton

@Singleton
class ConsoleNotifier : Notifier {

    @InjectProperty
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