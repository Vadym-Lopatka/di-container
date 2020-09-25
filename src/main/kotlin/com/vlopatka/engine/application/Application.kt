package com.vlopatka.engine.application

import com.vlopatka.engine.ObjectFactory
import com.vlopatka.engine.config.KotlinConfig

object Application {

    fun run(packageToScan: String, interfaceToImplementationMap: Map<Class<*>, Class<*>>): ApplicationContext {
        val config = KotlinConfig(packageToScan, interfaceToImplementationMap)
        val context = ApplicationContext(config)

        val factory = ObjectFactory(context)
        context.factory = factory

        return context
    }
}