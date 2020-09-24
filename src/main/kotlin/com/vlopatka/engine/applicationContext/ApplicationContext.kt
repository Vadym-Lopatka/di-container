package com.vlopatka.engine.applicationContext

import java.util.concurrent.ConcurrentHashMap

class ApplicationContext {
    private val cache: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()


    fun <T> getObject(type: Class<T>): T {
        return null as T
    }
}