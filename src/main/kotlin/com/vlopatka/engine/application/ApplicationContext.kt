package com.vlopatka.engine.application

import com.vlopatka.annotation.Singleton
import com.vlopatka.engine.ObjectFactory
import com.vlopatka.engine.config.KotlinConfig
import java.util.concurrent.ConcurrentHashMap

class ApplicationContext(val config: KotlinConfig) {
    private val cache: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()
    var factory: ObjectFactory? = null

    fun <T> getObject(type: Class<T>): T {

        if (cache.contains(type)) {
            return cache[type] as T
        }

        val implClass = type.takeIf { it.isInterface }?.let { config.getImplClass(type) } ?: type
        val obj = factory?.createObject(type) ?: throw IllegalStateException("factory is not initialized")

        if (implClass.isAnnotationPresent(Singleton::class.java)) {
            cache[type] = obj!!
        }

        return obj
    }

}