package com.vlopatka.engine.context

import com.vlopatka.engine.annotation.Singleton
import com.vlopatka.engine.config.KotlinConfig
import com.vlopatka.engine.factory.ObjectFactory
import java.util.concurrent.ConcurrentHashMap

class ApplicationContext(val config: KotlinConfig) {
    private val cache: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()
    var factory: ObjectFactory? = null

    fun <T> getObject(type: Class<T>): T {

        if (cache.containsKey(type)) {
            return cache[type] as T
        }

        val implClass = type.takeIf { it.isInterface }?.let { config.getImplClass(type) } ?: type
        val obj = factory?.createObject(implClass) ?: throw IllegalStateException("factory is not initialized")

        if (implClass.isAnnotationPresent(Singleton::class.java)) {
            cache[type] = obj
        }

        return obj
    }

}