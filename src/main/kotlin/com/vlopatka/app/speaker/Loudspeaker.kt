package com.vlopatka.app.speaker

interface Loudspeaker {
    fun notify(msg: String)
    fun periodicNotifier(msg: String, counter: Int = 10, pendingInMs: Long = 1000L)
}
