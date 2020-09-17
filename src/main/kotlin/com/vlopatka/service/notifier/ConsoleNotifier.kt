package com.vlopatka.service.notifier

class ConsoleNotifier : Notifier {
    override fun notify(msg: String) {
        println(msg)
    }

    override fun periodicNotifier(msg: String, counter: Int, pendingInMs: Long) {
        for (i in counter downTo 1) {
            println("$msg $i")
            Thread.sleep(pendingInMs)
        }
    }
}