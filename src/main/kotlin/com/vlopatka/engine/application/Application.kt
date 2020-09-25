package com.vlopatka.engine.application

import com.vlopatka.engine.ObjectFactory
import com.vlopatka.engine.config.KotlinConfig
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService

object Application {
    fun run(packageToScan: String, interfaceToImplementationMap: Map<Class<*>, Class<*>>): ApplicationContext {
        val config = KotlinConfig(
            packageToScan = packageToScan,
            interfaceToImplementationMap = defineImplementations()
        )

        val context = ApplicationContext(config)
        val factory = ObjectFactory(context)
        context.factory = factory

        return context
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
}