package com.vlopatka.dicontainer.context

import com.vlopatka.dicontainer.annotation.Singleton
import com.vlopatka.dicontainer.config.KotlinConfig
import com.vlopatka.dicontainer.factory.ObjectFactory
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