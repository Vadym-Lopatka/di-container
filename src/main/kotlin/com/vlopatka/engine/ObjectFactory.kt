package com.vlopatka.engine

import com.vlopatka.engine.config.KotlinConfig
import com.vlopatka.engine.objectConfigurator.ObjectConfigurator
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService
import java.lang.reflect.Type

object ObjectFactory {

    private val config = KotlinConfig(
        packageToScan = "com.vlopatka",
        interfaceToImplementationMap = defineImplementations()
    )

    private val configurators: List<ObjectConfigurator> = initConfigurators()


    fun <T> createObject(type: Class<T>): T {
        return if (type.isInterface) {
            buildObject(config.getImplClass(type))
        } else {
            buildObject(type)
        }
    }

    fun createObject(type: Type): Any? {
        println(type::class.java)
        return type
    }

    private fun <T> buildObject(implClass: Class<T>): T {
        val createdObject = implClass.declaredConstructors.first().newInstance()

        /**
         * Here I used a chain of responsibility pattern.
         *
         * Every DI component that would be created through @Singleton
         * will be processed once(on startup) by each implementation of the ObjectConfigurator.
         *
         * @see com.vlopatka.annotation.Singleton
         * @see com.vlopatka.engine.objectConfigurator.ObjectConfigurator
         */
        configurators.forEach { it.configure(createdObject) }

        return createdObject as T
    }

    /**
     * Define here which particular implementation to use when your interface has multiple implementations
     * @see com.vlopatka.service.security.SecurityService
     *
     * p.s. In prod-like products, it would be more convenient to read this map from an outer source.
     * For example,  a config file.
     */
    private fun defineImplementations(): Map<Class<*>, Class<*>> = mapOf(
        SecurityService::class.java to OutdoorSecurityService::class.java
    )

    private fun initConfigurators(): List<ObjectConfigurator> {
        val objConfiguratorImplementations = config.getScanner().getSubTypesOf(ObjectConfigurator::class.java)

        return objConfiguratorImplementations
            .map { it.declaredConstructors.first().newInstance() as ObjectConfigurator }
            .toList()
    }
}
