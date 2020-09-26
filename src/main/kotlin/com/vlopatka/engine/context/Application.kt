package com.vlopatka.engine.context

import com.vlopatka.engine.config.KotlinConfig
import com.vlopatka.engine.factory.ObjectFactory

object Application {

    fun run(packageToScan: String, interfaceToImplementationMap: Map<Class<*>, Class<*>>): ApplicationContext {
        val config = KotlinConfig(packageToScan, interfaceToImplementationMap)
        val context = ApplicationContext(config)

        val factory = ObjectFactory(context)
        context.factory = factory

        return context
    }
}