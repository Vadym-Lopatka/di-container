package com.vlopatka.domain.service.notifier

interface Notifier {
    fun notify(msg: String)
    fun periodicNotifier(msg: String, counter: Int = 10, pendingInMs: Long = 1000L)
}
