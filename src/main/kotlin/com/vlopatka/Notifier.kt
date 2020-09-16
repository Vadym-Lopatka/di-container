package com.vlopatka

interface Notifier {
    fun notify(msg: String)
    fun periodicNotifier(msg: String, counter: Int)
}
