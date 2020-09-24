package com.vlopatka.service.notifier

import com.vlopatka.annotation.Component
import com.vlopatka.annotation.InjectProperty

@Component
class ConsoleNotifier : Notifier {

    @InjectProperty
    private lateinit var greeting: String

    override fun notify(msg: String) {
        println("$greeting $msg")
    }

    override fun periodicNotifier(msg: String, counter: Int, pendingInMs: Long) {
        for (i in counter downTo 1) {
            println("$msg $i")
            Thread.sleep(pendingInMs)
        }
    }
}