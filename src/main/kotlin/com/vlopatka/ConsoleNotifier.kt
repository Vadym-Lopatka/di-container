package com.vlopatka

class ConsoleNotifier : Notifier {
    override fun notify(msg: String) {
        println(msg)
    }

    override fun periodicNotifier(msg: String, counter: Int) {
        for (i in counter downTo 1) {
            println("$msg $i")
            Thread.sleep(1000)
        }
    }
}