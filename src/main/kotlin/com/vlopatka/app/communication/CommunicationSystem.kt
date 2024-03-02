package com.vlopatka.app.communication

interface CommunicationSystem {
    fun notify(msg: String)
    fun notifyPeriodically(msg: String, times: Int = 10, pendingMs: Long = 1000L)
}
